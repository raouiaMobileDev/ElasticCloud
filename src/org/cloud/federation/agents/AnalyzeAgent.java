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
import jade.wrapper.StaleProxyException;

import org.cloud.federation.openforecast.ExponentialSmoothing;
import org.cloud.federation.utils.CpuUsage;
import org.cloud.federation.utils.EventUtil;

public class AnalyzeAgent extends Agent {

	/**
								 * 
								 */
	private static final long serialVersionUID = 1L;
	static int count;
	AgentController analyzeAgentPOPI = null;
	AgentController analyzeAgentPORI = null;
	AgentController analyzeAgentROPI = null;
	AgentController analyzeAgentRORI = null;
	
	int Nb_VMs;
	boolean scale_out;
	int CPUThdOut = 85;
	int CPUThdIN = 25;
	int windowsTime = 5;

	protected void setup() {

		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("AnalyzeAgent", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			CpuUsage.createFile();

			

		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
		setEnabledO2ACommunication(true, 0);

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {

				ACLMessage msgSendorAgent = receive();
				Object myObject = myAgent.getO2AObject();
				double cpuUsage = 0.0;

				if (myObject != null) {
					System.out
							.println("************ you are in Analyze Agent *******");
				}

				if (msgSendorAgent != null) {
					System.out.println("***********sender********"
							+ msgSendorAgent.getSender().getName() + " info  "
							+ msgSendorAgent.getSender().toString());
					
					String MonitorAgentContent = msgSendorAgent.getContent();
					
					
					
			
					if(analyzeAgentPOPI==null && analyzeAgentPORI==null  && analyzeAgentRORI==null && analyzeAgentROPI==null)
					{
						try {

                    jade.core.Runtime rt = jade.core.Runtime.instance();
                    AgentContainer ac = myAgent.getContainerController();
                    
                    /*	
                    // ************* analyzeAgentPOPI *************
							analyzeAgentPOPI = ac.createNewAgent("AnalyzeAgentPOPI",
							        "org.cloud.federation.agents.AnalyzeAgentPOPI",
							        null);
						
							analyzeAgentPOPI.start();
						*/	
						
                    /*
					// ************* analyzeAgentPORI *************
							analyzeAgentPORI = ac.createNewAgent("AnalyzeAgentPORI",
							        "org.cloud.federation.agents.AnalyzeAgentPORI",
							        null);
						
							analyzeAgentPORI.start();		
						*/	
                    
                    /*
                 // ************* analyzeAgentPORIAlways *************
                	analyzeAgentPORI = ac.createNewAgent("AnalyzeAgentROPIAlways",
					        "org.cloud.federation.agents.AnalyzeAgentROPIAlways",
					        null);
				
					analyzeAgentPORI.start();	
						*/	
					
                    /*
                    // ************* analyzeAgentPORIAlways *************
                	analyzeAgentPORI = ac.createNewAgent("AnalyzeAgentROPIPRAlways",
					        "org.cloud.federation.agents.AnalyzeAgentROPIPRAlways",
					        null);
				
					analyzeAgentPORI.start();	
						*/
                    
                    // ************* analyzeAgentRNOPNI *************
                	analyzeAgentPORI = ac.createNewAgent("AnalyzeAgentRNOPNI",
					        "org.cloud.federation.agents.AnalyzeAgentRNOPNI",
					        null);
				
					analyzeAgentPORI.start();
                    
                    
                    /*
					// ************* analyzeAgentRORI *************
							analyzeAgentRORI = ac.createNewAgent("AnalyzeAgentRORI",
							        "org.cloud.federation.agents.AnalyzeAgentRORI",
							        null);
						
							analyzeAgentRORI.start();		
					
							
					*/		
						/*		
					// ************* analyzeAgentROPI *************
							analyzeAgentROPI = ac.createNewAgent("AnalyzeAgentROPI",
							        "org.cloud.federation.agents.AnalyzeAgentROPI",
							        null);
						
							analyzeAgentROPI.start();		
						*/
								
						
						} catch (StaleProxyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					  /* 
                    ACLMessage msgrPOPI = new ACLMessage(ACLMessage.CFP);   
                    msgrPOPI.setContent(MonitorAgentContent);
                    msgrPOPI.addReceiver(new AID("AnalyzeAgentPOPI",AID.ISLOCALNAME));
                    send(msgrPOPI);
                    
                    
                   */
					/*
                    ACLMessage msgrPORI = new ACLMessage(ACLMessage.CFP);   
                    msgrPORI.setContent(MonitorAgentContent);
                    msgrPORI.addReceiver(new AID("AnalyzeAgentPORI",AID.ISLOCALNAME));
                    send(msgrPORI);
                    */
					/*
					ACLMessage msgrPORI = new ACLMessage(ACLMessage.CFP);   
                    msgrPORI.setContent(MonitorAgentContent);
                    msgrPORI.addReceiver(new AID("AnalyzeAgentROPIAlways",AID.ISLOCALNAME));
                    send(msgrPORI);
					*/
					
					/*
					ACLMessage msgrPORI = new ACLMessage(ACLMessage.CFP);   
                    msgrPORI.setContent(MonitorAgentContent);
                    msgrPORI.addReceiver(new AID("AnalyzeAgentROPIPRAlways",AID.ISLOCALNAME));
                    send(msgrPORI);
                    */
					
					ACLMessage msgrPORI = new ACLMessage(ACLMessage.CFP);   
                    msgrPORI.setContent(MonitorAgentContent);
                    msgrPORI.addReceiver(new AID("AnalyzeAgentRNOPNI",AID.ISLOCALNAME));
                    send(msgrPORI);
					
					 /*
                    ACLMessage msgrRORI = new ACLMessage(ACLMessage.CFP);   
                    msgrRORI.setContent(MonitorAgentContent);
                    msgrRORI.addReceiver(new AID("AnalyzeAgentRORI",AID.ISLOCALNAME));
                    send(msgrRORI);
                   */
                   /*
                    ACLMessage msgrROPI = new ACLMessage(ACLMessage.CFP);   
                    msgrROPI.setContent(MonitorAgentContent);
                    msgrROPI.addReceiver(new AID("AnalyzeAgentROPI",AID.ISLOCALNAME));
                    send(msgrROPI);
					*/
					

				}

				if (msgSendorAgent == null)
					block();
			}
		});

	}
}
