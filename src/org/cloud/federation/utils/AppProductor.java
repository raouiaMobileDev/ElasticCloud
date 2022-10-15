package org.cloud.federation.utils;

public class AppProductor {
	
	private String info=null;
	private String product=null;
	private String vendor=null;
	private String version=null;
	private String fullVersion= null;

	
	
	
	/*
	 * constructor
	 */
	public AppProductor(String product) {
		
		this.product = product;
	}
	
	
	public AppProductor() {
		// TODO Auto-generated constructor stub
	}


	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFullVersion() {
		return fullVersion;
	}
	public void setFullVersion(String fullVersion) {
		this.fullVersion = fullVersion;
	}


	@Override
	public String toString() {
		return "AppProductor [product=" + product + "]";
	}
	

}
