package org.cloud.federation.utils;

public class VirtualMachine {
	
	private String name=null;
	private String info=null;
	private String OS=null;
	private AppProductor application=null;
	private double CPU=0.0;
	private int RAM=0;
	private double storage=0.0;
	private String login=null;
	private String password=null;
	private double price=0.0;
	private int imageID=-1;
	private Graphic graphic=null;
	private NIC nic=null;
	private String providerID=null;

	
	
	
	
	public VirtualMachine() {
		this.application= new AppProductor();
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getProviderID() {
		return providerID;
	}
	public void setProviderID(String provider) {
		this.providerID = provider;
	}

	
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public AppProductor getApplication() {
		return application;
	}
	public void setApplication(AppProductor application) {
		//this.application.setInfo(application.getInfo()); 
		this.application.setProduct(application.getProduct());
		//this.application.setVendor(application.getVendor());
		//this.application.setVersion(application.getVersion());
	}
	public double getCPU() {
		return CPU;
	}
	
	public void setCPU(double cPU) {
		CPU = cPU;
	}
	public int getRAM() {
		return RAM;
	}
	public void setRAM(int rAM) {
		RAM = rAM;
	}
	public double getStorage() {
		return storage;
	}
	public void setStorage(double storage) {
		this.storage = storage;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Graphic getGraphic() {
		return graphic;
	}
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}
	public NIC getNic() {
		return nic;
	}
	public void setNic(NIC nic) {
		this.nic = nic;
	}
	
	@Override
	public String toString() {
		return "VirtualMachine [name=" + name + ", info=" + info + ", OS=" + OS
				+ ", application=" + application + ", CPU=" + CPU + ", RAM="
				+ RAM + ", storage=" + storage + ", login=" + login
				+ ", password=" + password + ", price=" + price + ", imageID="
				+ imageID + ", graphic=" + graphic + ", nic=" + nic
				+ ", provider=" + providerID + "]";
	}
	
	

}
