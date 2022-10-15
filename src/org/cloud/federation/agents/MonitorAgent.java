package org.cloud.federation.agents;

import org.cloud.federation.utils.EventUtil;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * 
 * @author fortman
 */
public class MonitorAgent extends Agent {


	protected void setup() {

		addBehaviour(new CyclicBehaviour(this) {
		public void action() {
			
			
			String eventFile="";
			
			
			 					Sigar sigar = new Sigar();

			 					Mem mem;
								try {
									
									mem = sigar.getMem();
									
									double memUsage=(mem.getUsed()*100)/mem.getTotal();
							
				 					CpuPerc cpuPerc = sigar.getCpuPerc();
				 					double cpuIdle= cpuPerc.getIdle()*100;
				 					double cpuUsage= 100- cpuIdle;
				 					
				 					cpuUsage=Math.round(cpuUsage * Math.pow(10,1)) / Math.pow(10,1);
				 					
				 					
				 					
				 					System.out.println("cpuIdle="+cpuIdle);
				 					System.out.println("cpuUsage="+cpuUsage);
				 					System.out.println("memUsage="+memUsage);
				 					
				 					
				 					eventFile= EventUtil.eventToString(cpuUsage, memUsage);
				 					
				 					
				 					
				 					
								} catch (SigarException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			 				
								System.out.println(eventFile);

								ACLMessage acl = new ACLMessage(ACLMessage.CFP);
								
								AID aid = new AID("AnalyzeAgent@192.168.25.140:1099/JADE", AID.ISGUID);
								aid.addAddresses("http://192.168.25.140:7778/acc");
								
								acl.addReceiver(aid);
								acl.setContent(eventFile);
						
								send(acl);
								try {
									Thread.sleep(15000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			
		}
		
	});

}

}