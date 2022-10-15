package org.cloud.federation.utils;



public class Conversation {
	

	private String user;
	private String session;
	

	/*
	private Vector<VirtualMachine> VMs_cfp;
	private Vector<VirtualMachine> VMs_proposal;
	private Vector<VirtualMachine> VMs_accpet;
	private Vector<VirtualMachine> VMs_info;
	*/
	private String ovfFile_cfp;
	private String ovfFile_proposal;
	private String ovfFile_accpet;
	private String ovfFile_info;
	
	public Conversation(String user, String session) {
		this.user = user;
		this.session = session;
		
	}
	
	public Conversation(String user, String session, String ovfFile_cfp ) {
		this.user = user;
		this.session = session;
		this.setOvfFile_cfp(ovfFile_cfp);
		
	}
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getOvfFile_cfp() {
		return ovfFile_cfp;
	}

	public void setOvfFile_cfp(String ovfFile_cfp) {
		this.ovfFile_cfp = ovfFile_cfp;
	}

	public String getOvfFile_proposal() {
		return ovfFile_proposal;
	}

	public void setOvfFile_proposal(String ovfFile_proposal) {
		this.ovfFile_proposal = ovfFile_proposal;
	}

	public String getOvfFile_accpet() {
		return ovfFile_accpet;
	}

	public void setOvfFile_accpet(String ovfFile_accpet) {
		this.ovfFile_accpet = ovfFile_accpet;
	}

	public String getOvfFile_info() {
		return ovfFile_info;
	}

	public void setOvfFile_info(String ovfFile_info) {
		this.ovfFile_info = ovfFile_info;
	}
	
	
	

	
	
/*	
	public Vector<VirtualMachine> getVMs_cfp() {
		return VMs_cfp;
	}
	public void setVMs_cfp(Vector<VirtualMachine> vMs_cfp) {
		VMs_cfp = vMs_cfp;
	}
	public Vector<VirtualMachine> getVMs_proposal() {
		return VMs_proposal;
	}
	public void setVMs_proposal(Vector<VirtualMachine> vMs_proposal) {
		VMs_proposal = vMs_proposal;
	}
	public Vector<VirtualMachine> getVMs_accpet() {
		return VMs_accpet;
	}
	public void setVMs_accpet(Vector<VirtualMachine> vMs_accpet) {
		VMs_accpet = vMs_accpet;
	}
	public Vector<VirtualMachine> getVMs_info() {
		return VMs_info;
	}
	public void setVMs_info(Vector<VirtualMachine> vMs_info) {
		VMs_info = vMs_info;
	}
	
*/
	
	
	

}
