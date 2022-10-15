package org.cloud.federation.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import org.cloud.federation.openforecast.ExponentialSmoothing;
import org.cloud.federation.utils.CpuUsage;
import org.cloud.federation.utils.DB_Conversation;
import org.cloud.federation.utils.Envelope;
import org.cloud.federation.utils.EventUtil;
import org.cloud.federation.utils.OVFUtil;

public class AnalyzeAgent extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String action=null;
	String condition= null;
	String file_ovf=null;
	static int count;
	AgentController managerAgent=null;
	int analyze;
	boolean scale_out;
	boolean sendManager;
	ExponentialSmoothing es=null;
	int CPUThdOut=0;
	
	protected void setup() {
	
		
		
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("AnalyzeAgent", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			
			CpuUsage.createFile();
			
			analyze=1;
			scale_out=false;
			sendManager=false;
			count=1;
			 CPUThdOut=0;
		 es=new ExponentialSmoothing();
			
			
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
		setEnabledO2ACommunication(true, 0);
		
		

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				
				ACLMessage msgSendorAgent = receive();
				Object myObject = myAgent.getO2AObject();
				
				
				
				
				double cpuUsage=0.0;
				double memUsage=0.0;
				
				if(myObject != null)
				{
		        	System.out.println("************ you are in Analyze Agent *******");
		        
		        	file_ovf=(String)myObject;
		        	/*
		        	Vector <String> vms=OVFUtil.listVirtualSystem(file_ovf);
		        	for(int i=0; i<vms.size(); i++)
		        	{
		        		
		        	}
		        	*/
		        	
		        	condition= OVFUtil.getConditionofRule(file_ovf);
		        	action= OVFUtil.getActionofRule(file_ovf);
		        	
		        	//System.out.println("Condition="+ condition);
		        	//System.out.println("action="+ action);
		        		        	
		       }
				
				if (msgSendorAgent != null) 
	        	{
					System.out.println("***********sender********"+ msgSendorAgent.getSender().getName() +" info  "+msgSendorAgent.getSender().toString());
					//System.out.println(msgSendorAgent.getContent());
							
					String	eventFile= msgSendorAgent.getContent();
					
					 cpuUsage=EventUtil.getCpuUsage(eventFile);
					// memUsage=EventUtil.getMemUsage(eventFile);
					
					
					//System.out.println("memUsage- monitor="+memUsage);
					//System.out.println(eventFile);
					
					DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
					Date today =new Date();
					
					if(analyze < 3 )
					{
						System.out.println("cpuUsage- monitor="+cpuUsage);
						CpuUsage.addMetric(cpuUsage+"",mediumDateFormat.format(today)+"",2+"" );
					if(condition !=null && action != null && analyze <3  )
					{
						String metric=OVFUtil.getMetric(condition);
						if( metric.equals("cpuUsage"))
						{
							
							boolean operator=OVFUtil.getOperatorGreaterMetric(condition);
							CPUThdOut=OVFUtil.extractNumber(condition);
							
							if(operator== true && CPUThdOut>0){
							if(cpuUsage > CPUThdOut)
							{
								try {
									Thread.sleep(18000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								System.out.println("number="+ CPUThdOut);
								if(managerAgent==null)
								{
								System.out.println("Create agent manager");
								//ManagerAgent ra = new ManagerAgent();
		                        jade.core.Runtime rt = jade.core.Runtime.instance();
		                        AgentContainer ac2 = myAgent.getContainerController();
		                        
								try {
									managerAgent = ac2.createNewAgent("ManagerAgent",
									        "org.cloud.federation.agents.ManagerAgent",
									        null);
									managerAgent.start();
								} catch (StaleProxyException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
								analyze= analyze+1;
		                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
		                        msgr.setContent("scale_out="+analyze);
		                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
		                        send(msgr);
		                        
		                        scale_out=true;
							}
							}
							else
							{
								System.out.println("Nothing ");
							}
						}
					}
					
					}
					
					if(analyze>=2)
					{
						
						if(count%analyze==0)
						{
						System.out.println("observed add Value= "+ cpuUsage);
						CpuUsage.addMetric(cpuUsage+"",mediumDateFormat.format(today)+"",3+"" );
						
						
						es.observedValues.add(cpuUsage);
						System.out.println("size of observedValues="+ es.observedValues.size());
						if(es.observedValues.size()%5==0 )
						{
							
							System.out.println(es.observedValues.toString());
							
							es.forecastObservedValues();
							
							System.out.println(es.forcastedValues.toString());
							
							es.makeMSEValues();
							
							System.out.println("MSEValues="+es.MSEValues.toString());
							
							double x=es.getBestAlpha();
							
							System.out.println(x);
							
							double forecastValue=es.forecast (x);
							
							System.out.println("forcasted value= "+forecastValue);
							
							if(forecastValue <= 25)
							{
								
		                        ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
		                        msgr.setContent("scale_in="+analyze);
		                        msgr.addReceiver(new AID("ManagerAgent",AID.ISLOCALNAME));
		                        analyze--;
		                        es.clear();
		                        send(msgr);
							} else
							{
								es.clear();
							}
						}
											
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
