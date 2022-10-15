package org.cloud.federation.utils;


import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class CnxOpenNebulaUtil implements java.io.Serializable{
	
	
	static int inc=1;
	static String login="oneadmin";
	static String password="fdf6662f691aab23f3e9e55c3080e4e9";
	static int imageID=12;
	/*
	 * create virtual machine
	 */
	public static Object[] allocateVM(VirtualMachine v, String s)
			throws XmlRpcException {
		//int cpu=1;
		//int ram=520;
		XmlRpcClient client = new XmlRpcClient();
		//int disk =(int)(3*1000);
	//	System.out.print("disk="+disk );
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		String template = "NAME = "+ s
				+ "\nCPU = "+ v.getCPU()
				+ "\nMEMORY = "+ v.getRAM()
				+ "\nDISK = [IMAGE_ID  = "+ imageID+"]"
			//	+ "\nDISK = [ TYPE  = fs, SIZE  = "+disk +", FORMAT = ext3 ]"
				+ "\nNIC = [NETWORK = \"private\"]"
				+ "\nGRAPHICS = [TYPE = \"vnc\",LISTEN = \"0.0.0.0\"]";

		Object[] params = { session, template, false };
		Object[] resultInfo = null;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.allocate",
						params);
				boolean success = (Boolean) result[0];
				System.out.println("success= "+success);
				if (success) {
					int id = (Integer) result[1];
					Object[] paramInfo = { session, id };
					resultInfo = (Object[]) client.execute("one.vm.info",
							paramInfo);
				}

			} catch (Exception e) {
				System.err.println(e);
			}
		return resultInfo;
	}
			
	
	
	
	
		
	public static boolean resizeVM(VirtualMachine v, int idVM)
			throws XmlRpcException {

		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		String template = "CPU ="+ v.getCPU();
				//+ "\nMEMORY = "+ v.getRAM();

		Object[] params = { session,idVM, template, true };
		boolean success =false;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.resize",
						params);
				 success = (Boolean) result[0];
			} catch (Exception e) {
				System.err.println(e);
			}
		return success;
	}
	
			
			
	public static boolean  suspend  (int vmId)  throws XmlRpcException 
	{
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		boolean success=false;

		Object[] params = { session,"suspend", vmId };
		Object[] resultInfo = null;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.action",
						params);
				 success = (Boolean) result[0];
			} catch (Exception e) {
				System.err.println(e);
			}
		return success;
	}
	
	
	
	public static boolean  reboot  (int vmId)  throws XmlRpcException 
	{
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		boolean success=false;

		Object[] params = { session,"reboot", vmId };
		Object[] resultInfo = null;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.action",
						params);
				 success = (Boolean) result[0];
			} catch (Exception e) {
				System.err.println(e);
			}
		return success;
	}
	
	
	public static boolean resume (int vmId)  throws XmlRpcException 
	{
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		boolean success=false;

		Object[] params = { session,"resume", vmId };
		Object[] resultInfo = null;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.action",
						params);
				 success = (Boolean) result[0];
				 
				 System.out.println("result[1]"+result[1]);
				 
				 System.out.println("result[2]"+result[2]);
				 
			} catch (Exception e) {
				System.err.println(e);
			}
		return success;
	}
	
	
	public static boolean poweroff (int vmId)  throws XmlRpcException 
	{
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		String session = login+":"+ password;
		String SERVER_URL = "http://localhost:2633/RPC2";
		boolean success=false;

		Object[] params = { session,"poweroff", vmId };
		Object[] resultInfo = null;
		
		try {
			config.setServerURL(new URL(SERVER_URL));
			client.setConfig(config);
				Object[] result = (Object[]) client.execute("one.vm.action",
						params);
				 success = (Boolean) result[0];
			} catch (Exception e) {
				System.err.println(e);
			}
		return success;
	}
	
	
	/*
	public static void main (String arg[])	
	{
	
		
		try {
			boolean poweroff= false;
			boolean resize= false;
			boolean resume= false;
			
			while(poweroff== false )
			{
				poweroff=poweroff(47);
			System.out.println("poweroff="+poweroff);
			    Thread.sleep(30000);
			}
			
			while(resize== false )
			{
			VirtualMachine vm= new VirtualMachine();
			vm.setCPU(2.0);
			//vm.setRAM(1024);
			resize = CnxOpenNebulaUtil.resizeVM(vm,47);
			System.out.println("resize="+resize);
			Thread.sleep(35000);
			}
			
			while(resume== false )
			{
				resume=resume(47);
				System.out.println("resume="+resume);
			}
			//Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	*/
	
	
	
	public static void main (String arg[])	
	{
	
		
		try {
		
			boolean resume= false;
			while(resume== false )
			{
				resume=resume(70);
				System.out.println("resume="+resume);
				Thread.sleep(5000);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	public static boolean resizeCPU(int idVM,double CPU )
	{
		boolean resizeCPU=false;
				
		try {
			boolean poweroff= false;
			boolean resize= false;
			boolean resume= false;
			
			while(poweroff== false )
			{
				poweroff=poweroff(idVM);
			System.out.println("poweroff="+poweroff);
			    Thread.sleep(30000);
			}
			
			while(resize== false )
			{
			VirtualMachine vm= new VirtualMachine();
			vm.setCPU(CPU);
			//vm.setRAM(1024);
			resize = CnxOpenNebulaUtil.resizeVM(vm,idVM);
			System.out.println("resize="+resize);
			Thread.sleep(35000);
			}
			
			while(resume== false )
			{
				resume=resume(idVM);
				System.out.println("resume="+resume);
			}
			resizeCPU=true;
			//Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				return resizeCPU;
		
	}
	
	
	
	
	
		

}
