package org.cloud.federation.utils;

import java.util.Vector;

public class Envelope {
	
	private String session;
	private String build;
	private String receive;
	private String type;
	private String login;
	private String password;
	private Vector<VirtualMachine> VMs;
	
	
	public Envelope() {
		VMs= new Vector<VirtualMachine>();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vector<VirtualMachine> getVMs() {
		return VMs;
	}
	public void setVMs(Vector<VirtualMachine> vMs) {
		//for(int i=0;i<vMs.size(); i++)
		//VMs.addElement(vMs.get(i));
		this.VMs=vMs;
	}
	

	

}
