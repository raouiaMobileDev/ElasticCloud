package org.cloud.federation.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;





public class Cart {
	
	public static void main (String args[])
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
		 System.out.println(courant.getText());
		 System.out.println(courant.getAttribute("time").getValue());
		 String second= getSecond(courant.getAttribute("time").getValue()) ;
		 System.out.println("second "+ second);
	
		 line_chart_dataset.addValue( (double)Double.parseDouble(courant.getText()) , "CPU usage Vs time" , second);  
		}

		JFreeChart lineChartObject = ChartFactory.createLineChart(
		   "CPU usage Vs time","Time",
		   "CPU %",
		   line_chart_dataset,PlotOrientation.VERTICAL,
		   true,true,false);


		try
		{
			int width = 640; /* Width of the image */
		    int height = 480; /* Height of the image */ 
		    File lineChart = new File( "/home/cloud/workspace/CloudPerformence/LineChart.png" ); 
		   	System.out.println( lineChart.getAbsolutePath());
		    ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
		    
		
		
		}
		catch (IOException e){

		//System.out.println("Problem in creating chart.");
			e.printStackTrace();
		}
		
	}
	
	
	public static String getSecond (String date)
	{
		int i=0;
		if( date.contains("AM"))
		  i=date.indexOf("AM");
		else
		i=date.indexOf("PM");
		 int debut= i-3;
		 String m= date.substring(debut, debut+2);
		return m;
	}



}
