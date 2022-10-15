package org.cloud.federation.utils;


public class EventUtil {
	

	 
	/*
	 * have OVF file: type CFP 
	 */
	 public static String eventToString (double cpuUsage, double memUsage)
	{
		
		String event_file="<Mertic name= \"cpuUsage\" unit= \"%\">"+ cpuUsage+"</Mertic> \n"+
					"<Mertic name= \"memUsage\" unit= \"%\">"+ memUsage+"</Mertic> \n";
		
		return event_file;	
	}
	
	
	 public static double getCpuUsage(String eventFile)
	{
		 	
		 double cpuUsage=0;
			String chaine="<Mertic ";
			
			for(int ii=0; ii<2; ii++){
			int i=eventFile.indexOf("<Mertic ");
			int j= eventFile.indexOf("</Mertic>");
			String m= eventFile.substring(i, j);
			//System.out.print("m"+m);
			if(m.contains("cpuUsage"))
			{
			m=m.replaceAll("<Mertic ", "");
			int k= m.indexOf(">");
			String p=m.substring(k,m.length());
			p=p.replaceAll(">", "");
			cpuUsage= Double.parseDouble(p);
			}
			}
			
			return cpuUsage;
	}
	
	 
	 
	 
	 public static double getMemUsage(String eventFile)
	{
		 	
		 double memUsage=0;
			String chaine="<Mertic ";
			
			for(int ii=0; ii<2; ii++){
			int i=eventFile.indexOf("<Mertic ");
			int j= eventFile.indexOf("</Mertic>");
			String m= eventFile.substring(i, j);
			if(m.contains("memUsage"))
			{
			m=m.replaceAll("<Mertic ", "");
			int k= m.indexOf(">");
			String p=m.substring(k,m.length());
			p=p.replaceAll(">", "");
			memUsage= Double.parseDouble(p);
			}
			}
			
			return memUsage;
	}
	

	 
}
