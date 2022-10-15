package org.cloud.federation.agents;

import java.text.DateFormat;
import java.util.Date;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.cloud.federation.openforecast.ExponentialSmoothing;
import org.cloud.federation.utils.CpuUsage;
import org.cloud.federation.utils.EventUtil;


public class AnalyzeAgentROPI extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int count;
	AgentController managerAgent=null;
	int Nb_VMs;
	boolean scale_out;
	int CPUThdOut=85;
	int CPUThdIN=25;
	int windowsTime=7;
	int tampon=0;

	
	protected void setup() {
	
		
		
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("AnalyzeAgentROPI", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			CpuUsage.createFile();
			
			Nb_VMs=1;
			count=1;
			 CPUThdOut=85;
			 CPUThdIN=25;
			 windowsTime=7;
			 tampon=0;
		 ExponentialSmoothing.clear();
			
			
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
		setEnabledO2ACommunication(true, 0);
		
		

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				
				ACLMessage msgSendorAgent = receive();
				Object myObject = myAgent.getO2AObject();
				double cpuUsage=0.0;
				
				
				if(myObject != null)
				{
		        	System.out.println("************ you are in Analyze Agent *******");	        	
		       }
				
				if (msgSendorAgent != null) 
	        	{
					System.out.println("***********sender********"+ msgSendorAgent.getSender().getName() +" info  "+msgSendorAgent.getSender().toString());
							
					String	MonitorAgentContent= msgSendorAgent.getContent();
					cpuUsage=EventUtil.getCpuUsage(MonitorAgentContent);
					
					
					DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
					Date today =new Date();
					
					
					// *****************Run 1 VM ***************
					if(Nb_VMs ==1 )
					{
						System.out.print("*****************Run 1 VM ***************");
						System.out.println("cpuUsage- monitor="+cpuUsage);
						CpuUsage.addMetric(cpuUsage+"",mediumDateFormat.format(today)+"",Nb_VMs+"" );
						
						// *****************reactive approach***************
						
						if(cpuUsage > CPUThdOut)
						{
							try {
							//	Thread.sleep(18000);
							if(tampon!=0)
							{
								System.out.println("the second detection of saturation state");
							
							if(managerAgent==null)
							{
							System.out.println("Create agent manager");
							ManagerAgent ra = new ManagerAgent();
	                        jade.core.Runtime rt = jade.core.Runtime.instance();
	                        AgentContainer ac2 = myAgent.getContainerController();
								managerAgent = ac2.createNewAgent("ManagerAgent",
								        "org.cloud.federation.agents.ManagerAgent",
								        null);
								managerAgent.start();
							}
							
							Nb_VMs= Nb_VMs+1;
							tampon=0;
	                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
	                        msgr.setContent("scale_out="+Nb_VMs);
	                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
	                        send(msgr);
							}
							else if(tampon==0)
							{
								tampon=1;
								System.out.println("the first detection of saturation state");
							}
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if(cpuUsage < CPUThdOut && tampon!=0)
							tampon=0;
						//*****************************************************						
					}
					
					
					// *****************Run 2 VMs *******************************
					if(Nb_VMs==2)
					{
						
						if(count%2==0) // to get CPU of 1 VM
						{
							System.out.print("*****************Run 2 VMs ***************");
						System.out.println("observed add Value= "+ cpuUsage);
						CpuUsage.addMetric(cpuUsage+"",mediumDateFormat.format(today)+"",Nb_VMs+"" );
						
						
						// *****************reactive approach***************
						if(cpuUsage > CPUThdOut)
						{
							//	Thread.sleep(18000);
							if(tampon!=0)
							{
								System.out.println("the second detection of saturation state");
							
							Nb_VMs= Nb_VMs+1;
							tampon=0;
	                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
	                        msgr.setContent("scale_out="+Nb_VMs);
	                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
	                        send(msgr);
	                        count=0;
							}
							else if(tampon==0)
							{
								tampon=1;
								System.out.println("the first detection of saturation state");
							}
								
						}
						if(cpuUsage < CPUThdOut && tampon!=0)
							tampon=0;
						//******************************************************
						
						// *****************Proactive approach***************
						ExponentialSmoothing.observedValues.add(cpuUsage);
						System.out.println("size of observedValues="+ ExponentialSmoothing.observedValues.size());
						if(ExponentialSmoothing.observedValues.size()%windowsTime==0 )
						{
							ExponentialSmoothing.forecastObservedValues();
							ExponentialSmoothing.makeMSEValues();
							System.out.println("MSEValues="+ExponentialSmoothing.MSEValues.toString());
							double x=ExponentialSmoothing.getBestAlpha();
							System.out.println(x);
							double forecastValue=ExponentialSmoothing.forecast (x);
							System.out.println("forcasted value= "+forecastValue);
							
							if(forecastValue <= CPUThdIN)
							{
								
		                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
		                        msgr.setContent("scale_in="+Nb_VMs);
		                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
		                        Nb_VMs--;
		                        ExponentialSmoothing.clear();
		                        send(msgr);
		                        count=0;
							} else
							{
								ExponentialSmoothing.clear();
							}
						}
						//******************************************************				
					}//if count
						count =count+1;
					}
					
					
					// *****************Run 3 VMs *******************************
					
					
					if(Nb_VMs==3)
					{
						
						if(count%3==0) // to get CPU of 1 VM
						{
							System.out.print("*****************Run 3 VMs ***************");
						System.out.println("observed add Value= "+ cpuUsage);
						CpuUsage.addMetric(cpuUsage+"",mediumDateFormat.format(today)+"",Nb_VMs+"" );
						
						
						// *****************Proactive approach***************
						ExponentialSmoothing.observedValues.add(cpuUsage);
						System.out.println("size of observedValues="+ ExponentialSmoothing.observedValues.size());
						if(ExponentialSmoothing.observedValues.size()%windowsTime==0 )
						{
							ExponentialSmoothing.forecastObservedValues();
							ExponentialSmoothing.makeMSEValues();
							System.out.println("MSEValues="+ExponentialSmoothing.MSEValues.toString());
							double x=ExponentialSmoothing.getBestAlpha();
							System.out.println(x);
							double forecastValue=ExponentialSmoothing.forecast (x);
							System.out.println("forcasted value= "+forecastValue);
							
							if(forecastValue <= CPUThdIN)
							{
								
		                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
		                        msgr.setContent("scale_in="+Nb_VMs);
		                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
		                        Nb_VMs--;
		                        ExponentialSmoothing.clear();
		                        send(msgr);
		                        count=0;
							} else
							{
								ExponentialSmoothing.clear();
							}
						}
						//******************************************************				
					}//if count
						count =count+1;
					}
					
					
				
					
			    }
				if(msgSendorAgent == null)
					block();
			}
		});

	}
}
