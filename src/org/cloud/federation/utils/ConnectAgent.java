package org.cloud.federation.utils;



import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class ConnectAgent {

public static void connexAgent (String file_ovf)
{

	Runtime myRuntime = Runtime.instance();

	// prepare the settings for the platform that we're going to connect to
	Profile myProfile = new ProfileImpl();
	myProfile.setParameter(Profile.MAIN_HOST, "192.168.25.140");
	myProfile.setParameter(Profile.MAIN_PORT, "1099");
	

	// create the agent container
	ContainerController myContainer = myRuntime.createAgentContainer(myProfile);
	Object myObject = file_ovf;
	try {
		AgentController myAgentController = myContainer.getAgent("UserAgent");
		myAgentController.putO2AObject(myObject, false);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}	
	
}
