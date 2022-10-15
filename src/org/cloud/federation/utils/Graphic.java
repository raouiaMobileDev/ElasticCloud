package org.cloud.federation.utils;

public class Graphic {
	private String listen =null;
	private String port =null;
	private String type =null;
	
	
	public Graphic(String listen, String port, String type) {
		super();
		this.listen = listen;
		this.port = port;
		this.type = type;
	}
	public Graphic() {
		// TODO Auto-generated constructor stub
	}
	public String getListen() {
		return listen;
	}
	public void setListen(String listen) {
		this.listen = listen;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
