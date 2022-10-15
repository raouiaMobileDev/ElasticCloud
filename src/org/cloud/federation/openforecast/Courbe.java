package org.cloud.federation.openforecast;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Courbe {
	
	
	
	
	public static void main (String args[])
	{
	
	
		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		
		line_chart_dataset.addValue( (double)Double.parseDouble("10.2") , "CPU usage Vs time" , 15+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("40.1") , "CPU usage Vs time" , 30+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("55.5") , "CPU usage Vs time" , 45+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("100") , "CPU usage Vs time" , 60+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("100") , "CPU usage Vs time" , 75+""); 
		
		line_chart_dataset.addValue( (double)Double.parseDouble("65.5") , "CPU usage Vs time" , 90+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("58.8") , "CPU usage Vs time" , 105+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("45.9") , "CPU usage Vs time" , 120+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("48.7") , "CPU usage Vs time" , 135+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("30.6") , "CPU usage Vs time" , 150+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("15.9") , "CPU usage Vs time" , 165+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("13.7") , "CPU usage Vs time" , 180+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("23.6") , "CPU usage Vs time" , 195+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("14.1") , "CPU usage Vs time" , 210+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("27.8") , "CPU usage Vs time" , 225+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("12.7") , "CPU usage Vs time" , 240+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("18.6") , "CPU usage Vs time" , 255+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("20.0") , "CPU usage Vs time" , 270+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("15.8") , "CPU usage Vs time" , 285+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("19.9") , "CPU usage Vs time" , 300+""); 

		line_chart_dataset.addValue( (double)Double.parseDouble("20.7") , "CPU usage Vs time" , 315+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("10.8") , "CPU usage Vs time" , 330+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("22.3") , "CPU usage Vs time" , 345+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("13.7") , "CPU usage Vs time" , 360+"");
		line_chart_dataset.addValue( (double)Double.parseDouble("19.2") , "CPU usage Vs time" , 375+"");
		
		
		
		line_chart_dataset.addValue( (double)Double.parseDouble("40.8") , "CPU usage Vs time" , 390+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("65.9") , "CPU usage Vs time" , 405+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("45.4") , "CPU usage Vs time" , 420+""); 
		line_chart_dataset.addValue( (double)Double.parseDouble("30.8") , "CPU usage Vs time" , 435+""); 
		
		
		
		JFreeChart lineChartObject = ChartFactory.createLineChart(
				  "","Time (seconds)",
				  "CPU usage %",
				  line_chart_dataset,PlotOrientation.VERTICAL,
				  true,true,false);
				String path ="";
		
		
		
		
		try
		{
			int width = 1504; /* Width of the image */
		   int height = 900; /* Height of the image */ 
		   File lineChart = new File( "/home/cloud/workspace/ElasticCloud/forecast.png" ); 
		  	System.out.println( lineChart.getAbsolutePath());
		   ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
		  
		}
		catch (IOException e){

		//System.out.println("Problem in creating chart.");
			e.printStackTrace();
		}
	}
	

}
