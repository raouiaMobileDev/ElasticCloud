package org.cloud.federation.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

import java.util.Vector;

import org.cloud.federation.utils.Envelope;
import org.cloud.federation.utils.OVFUtil;
import org.cloud.federation.utils.Provider;
import org.cloud.federation.utils.VirtualMachine;

public class NegotiationAgent extends Agent {

	private final String LOCAL_PROVIDER = "provider01";
	
	private AID managerAgent;

	protected void setup() {
		
		
		
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(new AID("NegotiationAgent", AID.ISLOCALNAME));
			DFService.register(this, dfd);
			
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
		
		

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				
				ACLMessage msg = receive();

				if (msg != null) {
					if (msg.getSender().getName()
							.contains("ManagerAgent")) {
						managerAgent=msg.getSender();
					ACLMessage acl = new ACLMessage(ACLMessage.CFP);
					AID r = new AID("ProviderAgent@192.168.25.20:1099/JADE", AID.ISGUID);
					r.addAddresses("http://192.168.25.200:7778/acc");
					acl.addReceiver(r);
					acl.setContent(msg
							.getContent());
					System.out.println("the message send to Provider Agent= "+msg
							.getContent());
					send(acl);
				}
				}
				else {
					ACLMessage msgr = new ACLMessage(
							ACLMessage.PROPOSE);
					msgr.setContent("toto");
				
					msgr.addReceiver(managerAgent);
					send(msgr);
				}
				if (msg == null) {
					block();
				}
			}
		});

	}

}
