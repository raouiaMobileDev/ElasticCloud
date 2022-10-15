package org.cloud.federation.utils;

public class NIC {

	private String ip=null;
	private String ip6=null;
	private String mac=null;
	private String network=null;
	
	
	public NIC(String ip, String ip6, String mac, String network) {
		super();
		this.ip = ip;
		this.ip6 = ip6;
		this.mac = mac;
		this.network = network;
	}
	
	public NIC() {
		// TODO Auto-generated constructor stub
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp6() {
		return ip6;
	}
	public void setIp6(String ip6) {
		this.ip6 = ip6;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	
}
