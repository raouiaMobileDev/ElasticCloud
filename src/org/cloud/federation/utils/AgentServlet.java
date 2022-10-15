/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2002 TILAB

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package org.cloud.federation.utils;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;



import java.io.IOException;


import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AgentServlet extends HttpServlet {


	public static AgentController analyzeAgent=null;
	
	
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		try {
			
			Boot.main(new String[] { "-gui", "jade.Boot",
					"org.cloud.federation.agents.AnalyzeAgent" });
			jade.core.Runtime rt = jade.core.Runtime.instance();
			
			Profile p = new ProfileImpl(false);
			AgentContainer ac = rt.createAgentContainer(p);
			
			analyzeAgent = ac.createNewAgent("AnalyzeAgent",
					"org.cloud.federation.agents.AnalyzeAgent", null);
			analyzeAgent.start();
			
			
			/*
			MonitorAgent pa = new MonitorAgent();
			Boot.main(new String[] { "-gui", "jade.Boot",
					"org.cloud.federation.agents.MonitorAgent" });
			jade.core.Runtime rt = jade.core.Runtime.instance();
			
			Profile p = new ProfileImpl(false);
			AgentContainer ac = rt.createAgentContainer(p);
			
			AgentController Agent = ac.createNewAgent("MonitorAgent",
					"org.cloud.federation.agents.MonitorAgent", null);
			Agent.start();
			
			AnalyzeAgent ua = new AnalyzeAgent();
			analyzeAgent = ac
					.createNewAgent(
							"AnalyzeAgent",
							"org.cloud.federation.agents.AnalyzeAgent",
							null);
			analyzeAgent.start();
			
			*/
			
			System.out.println("Jade Inited()");
			System.out.println("Start");
		} catch (Exception ex) {
			System.out.println("erreur");
			System.out.println(ex);
		}
	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws ServletException,
			IOException {
	
			}

}
