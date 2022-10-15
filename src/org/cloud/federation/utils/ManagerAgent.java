package org.cloud.federation.utils;


import java.net.URL;
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

import org.apache.xmlrpc.XmlRpcException;
import org.cloud.federation.nginx.ConfigNginx;
import org.cloud.federation.nginx.StartNgnix;
import org.cloud.federation.utils.CnxOpenNebulaUtil;
import org.cloud.federation.utils.JDOMUtil;
import org.cloud.federation.utils.VirtualMachine;

public class ManagerAgent extends Agent {
	
	String action=null;
	String condition= null;
	String file_ovf=null;
	static String login="oneadmin";
	static String password="fdf6662f691aab23f3e9e55c3080e4e9";
	AgentController negotiationAgent=null;
	
	
	protected void setup() {
		
		
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("ManagerAgent", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
	
		
		

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				
				
				
				System.out.println("we are in ManagerAgent");
			
				ACLMessage msg = receive();

				if (msg != null) {
					if (msg.getSender().getName()
							.contains("AnalyzeAgent")) {
						/*
						boolean resume=false;
						try {
							
							
							while(resume== false )
							{
								resume = CnxOpenNebulaUtil.resume(68);
								System.out.println("resume="+resume);
								Thread.sleep(1000);
							}
							
						*/
						 
						System.out.println("le message reçu par managerAgent "+ msg.getContent());
						if(msg.getContent().contains("scale_in"))
						{
							
							try {
								
								boolean suspended= false;
								if(msg.getContent().contains("2")){
									System.out.println("le message reçu par managerAgent condition scale_in=1 "+ msg.getContent());
								while(suspended== false )
								{
									suspended=CnxOpenNebulaUtil.suspend(70);
									System.out.println("suspended="+suspended);
									Thread.sleep(5000);
								}	
							
								StartNgnix.stop();
								if(ConfigNginx.openFile()== true)
								{
									boolean add=ConfigNginx.removeServer("192.168.25.151:8080");
									System.out.println(add);
									boolean save=ConfigNginx.saveFile();
								//	Thread.sleep(1000);
								}
								} else if(msg.getContent().contains("3"))
								{
								
									while(suspended== false )
									{
										suspended=CnxOpenNebulaUtil.suspend(71);
										System.out.println("suspended="+suspended);
										Thread.sleep(5000);
									}	
								
									StartNgnix.stop();
									if(ConfigNginx.openFile()== true)
									{
										boolean add=ConfigNginx.removeServer("192.168.25.152:8080");
										System.out.println(add);
										boolean save=ConfigNginx.saveFile();
									//	Thread.sleep(1000);
									}
								}
								StartNgnix.start();
								
								
							} catch (Exception e) {
								e.printStackTrace();
							}
								
						}
						else{ if(msg.getContent().contains("scale_out")){
						try {
							System.out.println(" scale_out "+ msg.getContent());
							boolean resume= false;
							if(msg.getContent().contains("2"))
							{
								System.out.println("scale_out=2"+ msg.getContent());
							while(resume== false )
							{
								resume=CnxOpenNebulaUtil.resume(70);
								System.out.println("resume="+resume);
								Thread.sleep(5000);
							}	
						
							StartNgnix.stop();
							if(ConfigNginx.openFile()== true)
							{
								boolean add=ConfigNginx.addServer("192.168.25.151:8080");
								System.out.println(add);
								boolean save=ConfigNginx.saveFile();
							}
							} else if(msg.getContent().contains("3"))
							{
								System.out.println("scale_out=3"+ msg.getContent());
								while(resume== false )
								{
									resume=CnxOpenNebulaUtil.resume(71);
									System.out.println("resume="+resume);
									Thread.sleep(5000);
								}	
							
								StartNgnix.stop();
								if(ConfigNginx.openFile()== true)
								{
									boolean add=ConfigNginx.addServer("192.168.25.152:8080");	
									System.out.println(add);
									boolean save=ConfigNginx.saveFile();
								}
							}
							StartNgnix.start();
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						}
						
					}
		/*		
				if(negotiationAgent==null)
				{
				System.out.println("Create agent manager");
				PlantAgent ra = new PlantAgent();
                jade.core.Runtime rt = jade.core.Runtime.instance();
                AgentContainer ac2 = myAgent.getContainerController();
                
				try {
					negotiationAgent = ac2.createNewAgent("NegotiationAgent",
					        "org.cloud.federation.agents.NegotiationAgent",
					        null);
					negotiationAgent.start();
				} catch (StaleProxyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
                ACLMessage msgr = new ACLMessage(ACLMessage.CFP);   
                msgr.setContent(file_ovf);
                msgr.addReceiver(new AID("NegotiationAgent",AID.ISLOCALNAME));
                send(msgr);
*/
	
				block();
					}
				}
				
				if(msg == null)
					block();
				
			}
		});

	}

}
