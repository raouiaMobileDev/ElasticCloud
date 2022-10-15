package org.cloud.federation.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;







public class CpuUsage {
	
	public static String FILE_NAME= "/home/cloud/workspace/ElasticCloud/database/vm1/cpuUsage.xml";
	
	public static Element database ;
	public static Document doc; 
	
	
	public static void createFile()
	{
		  try {
			database = new Element("database");
			doc = new Document(database);
			doc.setRootElement(database);
			Element metadata = new Element("metadata");
			metadata.addContent(new Element("name").setText("cpuUsage"));
			metadata.addContent(new Element("unit").setText("%"));
			metadata.addContent(new Element("vm").setText(""));
			 database.addContent(metadata);
			
			Element data = new Element("data");
			 database.addContent(data);
			
			
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(FILE_NAME));

			System.out.println("File Saved!");
			
			
		  }
		  catch (Exception e) {
		System.out.println(e.getStackTrace());
		  }
	}
	
	public static void addMetric(String cpuUsageValue, String date, String VMs){
		
		 SAXBuilder sxb = new SAXBuilder();
		 try {
		
			 doc = sxb.build(new File(FILE_NAME));
			
		 database = doc.getRootElement();
		 Element data=database.getChild("data");
		 
		 Element metric= new Element("metric");
			metric.setAttribute(new Attribute("time", date));
			metric.setAttribute(new Attribute("VMs", VMs));
			metric.setText(cpuUsageValue);
			data.addContent(metric);
			
			
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(FILE_NAME));

			//System.out.println("File Saved!");
			
			
		 } catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	
	
	 public static void main(String[] args)
	{
		CpuUsage.createFile();
	}
	

}
