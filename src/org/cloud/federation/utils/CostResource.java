package org.cloud.federation.utils;

public class CostResource {
	private int ram;
	private double cpu;
	private double price;
	
	
	public CostResource(int ram, double cpu, double price) {
		this.ram = ram;
		this.cpu = cpu;
		this.price = price;
	}
	
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public double getCpu() {
		return cpu;
	}
	public void setCpu(double cpu) {
		this.cpu = cpu;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
