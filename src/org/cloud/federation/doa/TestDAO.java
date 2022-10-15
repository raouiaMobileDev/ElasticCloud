package org.cloud.federation.doa;

import java.util.Date;
import java.util.List;

import org.cloud.federation.dao.*;
import org.cloud.federation.model.*;


public class TestDAO {
	
	public static void main(String args[])
	{

		
		try{
			
			AllocatedResourceDAO daoVM= new  AllocatedResourceDAO();
			//int x=daoVM.getVirtualMachineCount();
			AllocatedResource r=new AllocatedResource();
			r.setContext("message XML");
			r.setLogin("toto");
			r.setLogin("oneadmin");
			r.setPassword("fggkyu");
			r.setDate(new Date());
			
			daoVM.create(r);
		
		} catch(Exception e){e.printStackTrace();}
		
			
		
		 
	}

}

