package org.cloud.federation.utils;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.cloud.federation.doa.ResourceDAO;
import org.cloud.federation.model.Resource;




public class ConnectionUtil {

 //private final static String URL_SERVLET="http://localhost:8080/NegoFlowWS/servlet/HelloJavaWorld?param=agent";
	private final static String URL_SERVLET="http://192.168.25.140:8080/Cloud-Federation/Agent?param=UserAgent";
	public static void send(String message)
	{
		try {   
			URL aURL = new URL(URL_SERVLET);  
			URLConnection aConnection = aURL.openConnection();  
			aConnection.setDoInput(true);  
			aConnection.setDoOutput(true);  
			aConnection.setUseCaches(false);  
			aConnection.setDefaultUseCaches(false);  
			aConnection.setRequestProperty("Content-Type","application/octet-stream");  
		
			ObjectOutputStream out = new ObjectOutputStream(aConnection.getOutputStream());  
			out.writeObject(message);  
			out.flush();  
			out.close();  
			
			ObjectInputStream in = new ObjectInputStream(aConnection.getInputStream());  
			String text = (String) in.readObject();
			System.out.println("resultat="+ text);
			
			} catch (Exception e) {  
			//e.printStackTrace();  
			}  
}  

	public static void main(String[] args) {  
		ConnectionUtil.send("send servlet");
	}	
}
