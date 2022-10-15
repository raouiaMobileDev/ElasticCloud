package org.cloud.federation.nginx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ConfigNginx {
	
//	private String nginxFile="/etc/nginx/nginx.conf";
	public static String ngxConfig=null;
	
	
	public static  boolean openFile()
	{
		 ngxConfig="";
		String nginxFile="/etc/nginx/nginx.conf";
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
	
	public static boolean addServer( String IP)
	{
		
		 if(!ngxConfig.contains(IP))
		 {
		String tomcatservers= "upstream tomcatservers {";
		String TL="\n";
		String T="\t";
		int i= ngxConfig.indexOf(tomcatservers);
		
		String part1= ngxConfig.substring(0, i+tomcatservers.length());
		String part2= ngxConfig.substring(i+tomcatservers.length()+1, ngxConfig.length());
		
		String server= "server "+ IP+ ";";
		ngxConfig= part1+TL+T+server+TL+part2;
		 
		return true;
		 }
		 return false;
	}
	
	public static boolean removeServer( String IP)
	{
		String tomcatservers= "upstream tomcatservers {";
		int i= ngxConfig.indexOf(tomcatservers);
		String p=ngxConfig.substring(i+tomcatservers.length(), ngxConfig.length());
	//	System.out.println(p);
		int j= p.indexOf("}");
	//	System.out.println(j);
		String servers=ngxConfig.substring(i+tomcatservers.length(), i+tomcatservers.length()+j);
	//	System.out.println(servers);
		 if(servers.contains(IP))
		 {
			 
			 int ip= ngxConfig.indexOf(IP);
			 String server= ngxConfig.substring(ip-7, ip+IP.length()+1);
			 //System.out.println("removed server= "+ server);
			 
			 
				String part1= ngxConfig.substring(0, ip-7);
				String part2= ngxConfig.substring(ip+IP.length()+1, ngxConfig.length());
				ngxConfig= part1+part2;
				return true;
		 }
			 
		return false;
	}
	
	
	public static boolean saveFile()
	{
		PrintWriter writer=null;
		try {
			writer = new PrintWriter("/etc/nginx/nginx.conf", "UTF-8");
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
		// TODO Auto-generated method stub
		if(openFile()== true)
		{
			boolean add=addServer("192.168.25.151:8080");
			System.out.println(add);
			
			//boolean remove=removeServer("127.0.0.1:8280");
			boolean save=saveFile();
		}

	}

}
