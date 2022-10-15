package org.cloud.federation.nginx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ConfigPound {

//	private String nginxFile="/etc/nginx/nginx.conf";
	public static String ngxConfig=null;
	
	
	public static  boolean openFile()
	{
		 ngxConfig="";
		String nginxFile="/etc/pound.cfg";
		try{
			InputStream flux=new FileInputStream(nginxFile); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne="";
			while ((ligne=buff.readLine())!=null){
				ngxConfig +=ligne+"\n";
			}
			buff.close(); 
			}		
			catch (Exception e){
			System.out.println(e.toString());
			return false;
			}
		
		return true;
	}
	
	public static boolean addServer( String IP, String port)
	{
		
		 if(!ngxConfig.contains(IP))
		 {
		String tomcatservers= "Service";
		String TL="\n";
		String T="\t";
		int i= ngxConfig.indexOf(tomcatservers);
		
		String part1= ngxConfig.substring(0, i+tomcatservers.length());
		String part2= ngxConfig.substring(i+tomcatservers.length()+1, ngxConfig.length());
		
		String server= "BackEnd\n\t";
		server += "Address "+ IP+ "\n\t";
		server += "Port "+ port+ "\n\t";
		server += "End\n\t";
		ngxConfig= part1+TL+T+server+TL+part2;
		 
		return true;
		 }
		 return false;
	}
	
	public static boolean removeServer( String IP)
	{
		String tomcatservers= "Service";
		
		
		
		int ser= ngxConfig.indexOf(tomcatservers);
		int end= ngxConfig.length();
		String servers= ngxConfig.substring(ser+tomcatservers.length(), end);
		System.out.println(servers);
		
		String B[]=servers.split("BackEnd");
		boolean exit=false;
		for(int i=0; i<B.length||exit!=true; i++)
		{
			//System.out.println("B "+i);
			if(B[i].contains(IP))
			{
				String p="BackEnd"+B[i];
				 ngxConfig=ngxConfig.replaceFirst(p, "");
				System.out.print("extrait "+ngxConfig);
				exit=true;
			}
		}
	
		//servers.split("BackEnd");
		
		
		
		/*
		int i= ngxConfig.indexOf(IP);
		String p=ngxConfig.substring(i-IP.length()-3, i+IP.length()+23);
		System.out.println(p);

		String toto=ngxConfig.replaceFirst(p, "");
		System.out.println(toto);
		*/
		//String part1= ngxConfig.substring(0, i-tomcatservers.length()-10);
		//String part2= ngxConfig.substring(i+tomcatservers.length()+25, ngxConfig.length());
		//ngxConfig= part1+part2;
		//System.out.println(ngxConfig);
		
		
		
		
	//	System.out.println(p);
		//String key="BackEnd";
		//int kk= p.indexOf(key);
		//int jj=p.indexOf("End", kk+key.length());
		//int jj= p.indexOf("End");
		//String server= ngxConfig.substring(kk, jj);
		//System.out.println(server);
		 /*
	//	System.out.println(j);
		String servers=ngxConfig.substring(i+tomcatservers.length(), i+tomcatservers.length()+j);
	//	System.out.println(servers);
		 if(servers.contains(IP))
		 {
			 
			 int ip= ngxConfig.indexOf(IP);
			 String server= ngxConfig.substring(ip-15, ip+IP.length()+1);
			 //System.out.println("removed server= "+ server);
			 
			 
				String part1= ngxConfig.substring(0, ip-15);
				String part2= ngxConfig.substring(ip+IP.length()+15, ngxConfig.length());
				ngxConfig= part1+part2;
				return true;
		 }
		*/	 
		return false;
	}
	
	
	public static boolean saveFile()
	{
		PrintWriter writer=null;
		try {
			writer = new PrintWriter("/etc/pound.cfg", "UTF-8");
			//System.out.print(ngxConfig);
			writer.println(ngxConfig);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 	
	}

	public static void main(String[] args) 
	{
		 //TODO Auto-generated method stub
		if(openFile()== true)
		{
		boolean add=addServer("192.168.25.151", "8080");
		boolean add2=addServer("192.168.25.152", "8080");
		//	System.out.println(add);
			
		boolean remove=removeServer("192.168.25.151");
		boolean remove2=removeServer("192.168.25.152");
		boolean save=saveFile();
		}

	}	
	
	
	
	
}
