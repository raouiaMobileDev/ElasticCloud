package org.cloud.federation.nginx;

import java.sql.* ;
import java.io.*; 

import  org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities ;
import org.jfree.chart.JFreeChart ;
import  org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.ApplicationFrame;

import java.util.List ;
import java.util.Iterator;

import org.jdom.* ;
import org.jdom.input.*;
import org.jdom.filter.*;
import org.cloud.federation.utils.*;



public class JChart {
	
	
	public static void main()
	{
		
		
	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

	Element racine = new Element("database");
	org.jdom.Document document = new Document(racine);

	SAXBuilder sxb = new SAXBuilder();
	try
	{
		 document = sxb.build(new File(CpuUsage.FILE_NAME));
		 racine = document.getRootElement();
	}
	catch(Exception e){}
			 
	Element data= racine.getChild("data");

	List listMetrics = data.getChildren("metric");


	Iterator i = listMetrics.iterator();
	while(i.hasNext())
	{
	Element courant = (Element)i.next();

	String date=courant.getAttribute("time").getValue();

	int k=0;
	if( date.contains("AM"))
	  k=date.indexOf("AM");
	else
	k=date.indexOf("PM");
	 int debut= k-3;
	 String second= date.substring(debut, debut+2);

	line_chart_dataset.addValue( (double)Double.parseDouble(courant.getText()) , "CPU usage Vs time" , second);  
	}

	JFreeChart lineChartObject = ChartFactory.createLineChart(
	  "CPU usage Vs time","Time",
	  "CPU %",
	  line_chart_dataset,PlotOrientation.VERTICAL,
	  true,true,false);
	String path ="";

	try
	{
		int width = 640; /* Width of the image */
	   int height = 480; /* Height of the image */ 
	   File lineChart = new File( "/home/cloud/workspace/ElasticCloud/LineChart.png" ); 
	  	System.out.println( lineChart.getAbsolutePath());
	   ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
	   /*
	   ServletContext context = getServletContext(); // Inherited from HttpServlet.
	   if(context!=null)
	   {
	  path = context.getResource("/home/cloud/workspace/CloudPerformence/LineChart.png").getPath();
	   InputStream content = context.getResourceAsStream("/home/cloud/workspace/CloudPerformence/LineChart.png");
	   }
	    */
	   
	   
	}
	catch (IOException e){

	//System.out.println("Problem in creating chart.");
		e.printStackTrace();
	}
	
	}

}
