package org.cloud.federation.utils;

public class Provider {
	private String id=null;
	private String address=null;
	private String providerAgent=null;
	

	public Provider(String id, String address, String prividerAgent) {
		this.id = id;
		this.address = address;
		this.providerAgent = prividerAgent;
	}
	
	public Provider(String id, String address) {
		this.id = id;
		this.address = address;
	}
	
	public Provider() {
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProviderAgent() {
		return providerAgent;
	}
	public void setProviderAgent(String prividerAgent) {
		this.providerAgent = prividerAgent;
	}


	@Override
	public String toString() {
		return "Provider [id=" + id + ", address=" + address
				+ ", prividerAgent=" + providerAgent + "]";
	}
	
	
	
}
