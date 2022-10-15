package org.cloud.federation.agents;

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
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.cloud.federation.nginx.ConfigNginx;
import org.cloud.federation.nginx.StartNgnix;
import org.cloud.federation.utils.Capacity;
import org.cloud.federation.utils.CnxOpenNebulaUtil;
import org.cloud.federation.utils.DB_Conversation;
import org.cloud.federation.utils.Envelope;
import org.cloud.federation.utils.EventUtil;
import org.cloud.federation.utils.Graphic;
import org.cloud.federation.utils.JDOMUtil;
import org.cloud.federation.utils.NIC;
import org.cloud.federation.utils.OVFUtil;
import org.cloud.federation.utils.VirtualMachine;

public class PlantAgent extends Agent {
	
	String action=null;
	String condition= null;
	String file_ovf=null;
	static String login="oneadmin";
	static String password="fdf6662f691aab23f3e9e55c3080e4e9";
	
	
	protected void setup() {
		
		
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("PlanAgent", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
	
		
		

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				
				ACLMessage msgPlanAgent = receive();
				System.out.println("we are in ManagerAgent");
			
				
				/*
					boolean resize = CnxOpenNebulaUtil.resizeCPU(47, 2.0);
					System.out.println("resize VM="+ resize);
				*/
				
				
				
				
				Object[] response= null;
				VirtualMachine v= new VirtualMachine();
				v.setCPU(1.00);
				v.setRAM(1024);
				try {
					response=CnxOpenNebulaUtil.allocateVM(v, "LB3");
					if((Boolean)response[0]==true)
					{
						//listResponseResources.addElement((String)response[1]);
						String xml_info= (String)response[1];
						String IP= JDOMUtil.getNICIP(xml_info);
						String IPServer=IP+":8080";
						System.out.printf(IPServer);
					
						if(ConfigNginx.openFile()== true)
						{
							boolean add=ConfigNginx.addServer(IPServer);
							System.out.println(add);
							boolean save=ConfigNginx.saveFile();
							if(save== true)
							{
								System.out.println(StartNgnix.start());
							}
						}
						
						
						
						
						
						
						
					}
					
					
					
				} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				/*
				XmlRpcClient client = new XmlRpcClient();
				XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
				String session = login+":"+ password;
				String SERVER_URL = "http://localhost:2633/RPC2";
				boolean success=false;

				Object[] params = { session,"poweroff", 47 };
				Object[] resultInfo = null;
				
				try {
					config.setServerURL(new URL(SERVER_URL));
					client.setConfig(config);
						Object[] result = (Object[]) client.execute("one.vm.action",
								params);
						 success = (Boolean) result[0];
					} catch (Exception e) {
						System.err.println(e);
					}
				
				*/	
				block();
			//	System.out.println(msgPlanAgent.getContent());
				
				if(msgPlanAgent == null)
					block();
				
			}
		});

	}

}
