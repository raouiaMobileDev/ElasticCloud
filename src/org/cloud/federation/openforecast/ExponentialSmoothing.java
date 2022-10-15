package org.cloud.federation.openforecast;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.cloud.federation.utils.CpuUsage;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


public class ExponentialSmoothing {
	
	public static double alpha=0.0;
	public static Vector<Double> observedValues= new Vector<Double>();
	public static Vector<Vector<Double>>  forcastedValues= new Vector<Vector<Double>>();
	
	public static  Vector<Double> MSEValues= new Vector<Double>();
	//public static  double [] alphaValues =  {0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95};
	public static  double [] alphaValues =  {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
	
	public void ExponentialSmoothing()
	{
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		MSEValues.removeAllElements();
		}
	}
	
	public static void clear()
	{
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		MSEValues.removeAllElements();
		}
	}
	
	
	public static Vector<Double> forecastObservedValuesOfAlpha (double alpha )
	{
		double forecast=0.0;
		Vector<Double> forcastedValue=new Vector<Double>();
		for(int i=0; i<observedValues.size(); i++) {
		if ( forcastedValue.size()==0){
            forecast = observedValues.get(0);
            forcastedValue.add(formatValue(Double.valueOf(forecast)));
		}  
        else {
            forecast
                = alpha* observedValues.get(i-1)
                	+ (1.0-alpha)*forcastedValue.get(i-1);
            forcastedValue.add(formatValue(Double.valueOf(forecast)));
        }
		}
		return forcastedValue;	
	 }
	
	
	public static Double forecastValueOfAlpha (double observedValue, double forcastedValue, double alpha )
	{
		
		double  forecast
                = alpha* observedValue
                	+ (1.0-alpha)*forcastedValue;
          
		return forecast;	
	}
	
	
	
	public static void forecastObservedValues()
	{
		for(int i=0; i<alphaValues.length; i++){
			forcastedValues.add(forecastObservedValuesOfAlpha (alphaValues[i]));
		}
	}
	
	
	
	public static double forecast (double alpha )
	{
		double forecast=0.0;
		
		int index=-1;
		for(int i=0; i<alphaValues.length; i++){
		if(alphaValues[i]==alpha)
			index=i;
		}
		Vector<Double>	forcasteddValues= forcastedValues.get(index);
		if ( forcasteddValues.size()==0 && observedValues.size()==1 ){
            forecast = observedValues.get(0);
		}  
        else {
            forecast
                = alpha* observedValues.get((observedValues.size()-1))
                	+ (1.0-alpha)*forcasteddValues.get((observedValues.size()-1));
        }
		
		return formatValue(forecast);
	}
	
	
	public static void addObservedValue(double observedValue)
	{
		observedValues.add(Double.valueOf(observedValue));
	}
	
	
	public static double MSE(double alpha)
	{
		double MSE;
		double df=0.0;
		
		int index=-1;
		for(int i=0; i<alphaValues.length; i++){
		if(alphaValues[i]==alpha)
			index=i;
		}
		Vector<Double>	forcasteddValues= forcastedValues.get(index);
		
		if ( forcasteddValues.size()==0 || forcasteddValues.size()!= observedValues.size())
			return -0.0;
		else {
			for(int i=0; i< forcasteddValues.size(); i++)
				df+=Math.pow((forcasteddValues.get(i)-observedValues.get(i)),2);
		}
		MSE= df/observedValues.size();
		return formatValue(MSE);
		
	}
	
	
	
	public static void makeMSEValues()
	{	
		for(int i=0; i<alphaValues.length; i++){
			MSEValues.add( MSE(alphaValues[i]));	
		}
	}
	
	
	public static double getBestAlpha()
	{
		double bestAlpha=2.0;
		
		if(MSEValues.size()==0)
			makeMSEValues();
		
		double MinValue = 300;
		for(int i=0; i<MSEValues.size(); i++){
			if(i==0){
				 MinValue= (double)MSEValues.get(i);
				 bestAlpha= alphaValues[i];
			}
			else
			{
				if(MSEValues.get(i)<MinValue) {
					MinValue= MSEValues.get(i);
					bestAlpha= alphaValues[i];
				}
			}
		}
		
		return bestAlpha;
	}


	public static double getBestForcast( Vector<Double> observedValues, Vector<Double> forcasteddValues)
	{
		return  forecast (getBestAlpha() );
	}

	public static double formatValue(double value)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format(value));
	}

	
	
	
	public static void main(String[] args) {
	
		Jchart2();
		Jchart3();
		
	}
	
	
	
	
	/*
	public static void main(String[] args) {
		ExponentialSmoothing es=new ExponentialSmoothing();


	/*
		es.observedValues.add(20.8);
		    
		    
		es.observedValues.add(15.0);
		    
		    
		es.observedValues.add(12.8);
		    
		    
		es.observedValues.add(12.8);
		    
		 
		es.observedValues.add(13.0);
		    
		  
		es.observedValues.add(20.1);
		    
		   
		es.observedValues.add(26.1);
		  
		
		
		   
		es.observedValues.add(28.3);
		    
		   
		es.observedValues.add(30.5);
		    
		es.observedValues.add(20.7);
		    
		es.observedValues.add(14.3);
		    
		es.observedValues.add(20.3);
		  
		es.observedValues.add(22.5);
		    
			    
		es.observedValues.add(33.0);
		    
		   
		es.observedValues.add(35.7);
		    
		   
		es.observedValues.add(30.3);
		    
		   
		es.observedValues.add(28.7);
		    
		    
		es.observedValues.add(24.5);
		    
		    
		es.observedValues.add(22.0);
		    
		es.observedValues.add(16.7);
		    
		   
		es.observedValues.add(14.3);
		    
		  
		es.observedValues.add(16.3);
		    
		    
		es.observedValues.add(10.0);
		    
		    
		es.observedValues.add(15.3);
		    
		    
		es.observedValues.add(15.3);
		    
		    
		es.observedValues.add(12.0);
		    
		   
		es.observedValues.add(14.3);
		    
		  
		es.observedValues.add(20.8);
		    
		    
		es.observedValues.add(19.1);
		    
		es.observedValues.add(16.4);
		    
		   
		es.observedValues.add(11.4);
		    
		   
		es.observedValues.add(16.5);
		    
		    
		es.observedValues.add(13.0);
		    

		es.observedValues.add(20.7);
		    

		es.observedValues.add(22.9);
		    
		   
		es.observedValues.add(33.2);
		    
		    
		es.observedValues.add(33.8);
		    
		    
		es.observedValues.add(25.7);
		    
		   
		es.observedValues.add(22.0);
		    
		  
		es.observedValues.add(21.0);
		
	
		
		
		
		 other database
		es.observedValues.add(19.6);
		es.observedValues.add(23.3);
		es.observedValues.add(68.8);
		es.observedValues.add(15.9);
		es.observedValues.add(52.1);
		es.observedValues.add(36.4);
		es.observedValues.add(40.8);
		
		
		
		
		es.observedValues.add(100.0);
		es.observedValues.add(100.0);
		es.observedValues.add(23.9);
		es.observedValues.add(42.6);
		es.observedValues.add(37.0);
		es.observedValues.add(69.6);
		es.observedValues.add(20.8);
		es.observedValues.add(28.8);
		es.observedValues.add(4.0);
		es.observedValues.add(10.6);
		es.observedValues.add(4.1);
		es.observedValues.add(2.0);
		es.observedValues.add(6.4);
		es.observedValues.add(2.0);
		
		System.out.println(observedValues.toString());
		
		es.forecastObservedValues();
		
		System.out.println(forcastedValues.toString());
		
		makeMSEValues();
		
		System.out.println(MSEValues.toString());
		
		double x=getBestAlpha();
		
		System.out.println(x);
		
		System.out.println("forcasted value= "+es.forecast (x));
		
		
		Jchart();
		
	}
	*/
	
	public static double ES(double yy[])
	{
		clear();
		for(int i=0; i<yy.length;i++)
		observedValues.add(yy[i]);
	
		forecastObservedValues();
		makeMSEValues();
		double x=getBestAlpha();
		double forecastValue=forecast (x);
		return forecastValue;
	}
	
	public static double getExponentialSmoothing(double y[])
	{
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		MSEValues.removeAllElements();
		}
		for(int i=0; i<y.length; i++)
		{
			observedValues.add(y[i]);
		}
		
		forecastObservedValues();
		makeMSEValues();
		System.out.println(MSEValues.toString());		
		double value=getBestAlpha();		
		System.out.println(value);		
		return forecast (value);
	}
	
	
	public static void Jchart()
	{
		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		int x=-1;
		for(int i=0; i<observedValues.size();i++)
		{
		 x=i+1;
		line_chart_dataset.addValue( observedValues.get(i), "observed values" , new Integer(x));  
		}
		
		for(int i=0; i<forcastedValues.size(); i++)	
		{
			for(int j=0; j<forcastedValues.get(i).size(); j++)
			{
		int n=j+1;
		line_chart_dataset.addValue( forcastedValues.get(i).get(j), "f(α="+alphaValues[i]+")" , new Integer(n));  
			}
		}
		
		
		

		JFreeChart lineChartObject = ChartFactory.createLineChart(
		  "","The length of the time series (40 observations)",
		  "CPU usage %",
		  line_chart_dataset,PlotOrientation.VERTICAL,
		  true,true,false);
		String path ="";
		
		//lineChartObject.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot=lineChartObject.getCategoryPlot();
		plot.setBackgroundPaint(Color.white);
		
		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		
		
		
		
		
		
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(1, Color.PINK);
		renderer.setSeriesPaint(3, Color.YELLOW);
		renderer.setSeriesPaint(4, Color.cyan);
		renderer.setSeriesPaint(5, Color.ORANGE);
		renderer.setSeriesPaint(6, Color.CYAN);
		renderer.setSeriesPaint(7, Color.BLUE);
		renderer.setSeriesPaint(8, Color.MAGENTA);
		renderer.setSeriesPaint(9, Color.YELLOW);
		

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		renderer.setSeriesStroke(4, new BasicStroke(2.0f));
		renderer.setSeriesStroke(5, new BasicStroke(2.0f));
		renderer.setSeriesStroke(6, new BasicStroke(2.0f));
		renderer.setSeriesStroke(7, new BasicStroke(2.0f));
		renderer.setSeriesStroke(8, new BasicStroke(2.0f));
		renderer.setSeriesStroke(9, new BasicStroke(2.7f));

		plot.setRenderer(renderer);
		
		
		plot.setOutlinePaint(Color.BLACK);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		
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
	
	
	public static void Jchart2()
	{
		//double[] xb = {15, 30, 45, 60, 75, 90,105,120,135,150,165,180,195,210,225,240,255,270,285,300,315, 330, 345, 360, 375, 390, 405, 420, 435, 450, 465, 480 };
    	//double[] yb = { 20.8, 15.0, 12.8, 12.0, 13.0, 20.1, 26.1, 28.3, 30.5, 20.7, 25.3, 20.3, 22.5, 10.0, 20.7, 25.3, 28.7, 25.5, 22.0, 20.7, 14.3, 16.3, 10.0, 15.3, 12.0, 14.3, 20.8, 40.1, 38.4, 37.4, 35.5, 34.0 };
    
		
		//double[] xb = {15, 30, 45, 60, 75, 90,105,120,135,150,165,180,195,210,225,240,255,270,285,300,315, 330 /*, 345, 360, 375, 390, 405, 420, 435, 450, 465, 480*/ };
    	//double[] yb = { 20.8, 15.0, 12.8, 12.0, 13.0, 20.1, 26.1, 28.3, 30.5, 20.7, 14.3, 20.3, 22.5, 33.0, 35.7, 30.3, 28.7, 24.5, 22.0, 16.7, 14.3, 16.3 /*, 10.0, 11.3, 12.0, 14.3, 20.8, 19.1, 16.4, 11.4, 16.5, 13.0*/ };
		
		
		double[] xb = {210,225,240,255,270,285,300,315, 330, 345, 360, 375, 390, 405};
    	double[] yb = {100.0, 100.0, 23.9, 42.6, 37.0, 69.6, 20.8, 28.8, 4.0, 10.6, 4.1, 2.0, 6.4, 2.0};
		
		
		
    	Vector<Double> ES= new Vector<Double>();
    	Vector<Double> PR= new Vector<Double>();
    //	Vector<Double> RAMA= new Vector<Double>();
    	Vector<Double> ob= new Vector<Double>();
    	
		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		for(int i=7; i<yb.length;i++)
		{
			line_chart_dataset.addValue(yb[i], "observed values" , new Integer((int)xb[i]));  
			ob.add(yb[i]);
		}
	
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		MSEValues.removeAllElements();
		}
		for(int i=0; i<yb.length; i++)
		{
			observedValues.add(yb[i]);
		}
		
		forecastObservedValues();
		makeMSEValues();
		System.out.println(MSEValues.toString());		
		double cc=getBestAlpha();		
		System.out.println(cc);		
	//	return forecast (value);
		
	
		
		//*********ExponentialSmoothing************ 
		for(int j=0; j<xb.length-7; j++)
    	{
    		double[] xx= new double[7];
    		double[] yy= new double[7];
    		int k=0;
    		int i;
    		for(i=j; i<j+7;i++)
    		{
    			xx[k]=xb[i];
    			yy[k]=yb[i];
    			k++;
    		}
    	//	System.out.println(xx);
    	//	System.out.println(yy);
    		//PolynomialRegression regression = new PolynomialRegression(xx, yy, 2);
            double value=getExponentialSmoothing(yy);
           // System.out.println("xb[i]= "+xb[i]);
            line_chart_dataset.addValue(forecast (cc), "Exponential Smoothing" , new Integer((int) xb[i]));  
            ES.add(value);
    	}
		//************************************************************************************
		
	
		//*********PolynomialRegression*****************************************************
		for(int j=0; j<xb.length-7; j++)
    	{
    		double[] xx= new double[7];
    		double[] yy= new double[7];
    		int k=0;
    		int i;
    		for(i=j; i<j+7;i++)
    		{
    			xx[k]=xb[i];
    			yy[k]=yb[i];
    			k++;
    		}
    		//System.out.println(xx);
    		//System.out.println(yy);
    		PolynomialRegression regression = new PolynomialRegression(xx, yy, 2);
            double value=regression.predict(xb[i]);
            //System.out.println("xb[i]= "+xb[i]);
            line_chart_dataset.addValue(value, "Polynomial Regression" , new Integer((int) xb[i]));  
            PR.add(value);
    	}
		
/*
		//********ARMA*****************************************************
		ARMA arma =new ARMA();
		Vector<Double> observedvalues= new Vector<Double> ();
		for(int i=0; i<yb.length; i++)
		{
			observedvalues.add(yb[i]);
		}
		
		Vector<Double> forcastedValues=arma.forecast(observedvalues);
	//	System.out.println("forcastedValues="+ forcastedValues);
		for(int i=7; i<xb.length; i++)
    	{
            line_chart_dataset.addValue(forcastedValues.get(i), "RAMA" , new Integer((int) xb[i])); 
            RAMA.add(forcastedValues.get(i));
    	}
		//*******************************************************************************
	*/
		
		double MSE_ES;
		double MSE_PR;
		double MSE_RAMA;
		double dfES=0.0;
		double dfPR=0.0;
		//double dfRAMA=0.0;
		
	
			for(int i=0; i< ob.size(); i++)
			{
				dfES+=Math.pow((ES.get(i)-ob.get(i)),2);
				dfPR+=Math.pow((PR.get(i)-ob.get(i)),2);
				//dfRAMA+=Math.pow((RAMA.get(i)-ob.get(i)),2);
			}
				MSE_ES= dfES/ob.size();
				MSE_PR= dfPR/ob.size();
			//	MSE_RAMA= dfRAMA/ob.size();
			System.out.println("MSE_ES="+MSE_ES);
			System.out.println("MSE_PR="+MSE_PR);
			//System.out.println("MSE_RAMA="+MSE_RAMA);
		
		
		
		JFreeChart lineChartObject = ChartFactory.createLineChart(
				  "","Time (Seconds)",
				  "CPU usage %",
				  line_chart_dataset,PlotOrientation.VERTICAL,
				  true,true,false);
				String path ="";
				
				//lineChartObject.setBackgroundPaint(Color.WHITE);
				CategoryPlot plot=lineChartObject.getCategoryPlot();
				plot.setBackgroundPaint(Color.white);
				
				
				plot.setRangeGridlinesVisible(true);
				plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

				plot.setDomainGridlinesVisible(true);
				plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		
				
				
				
				
				LineAndShapeRenderer renderer = new LineAndShapeRenderer();

				// sets paint color for each series
				renderer.setSeriesPaint(0, Color.RED);
				renderer.setSeriesPaint(1, Color.DARK_GRAY);
				renderer.setSeriesPaint(1, Color.MAGENTA);
				renderer.setSeriesPaint(3, Color.CYAN);
				renderer.setSeriesPaint(4, Color.cyan);
				renderer.setSeriesPaint(5, Color.ORANGE);
				renderer.setSeriesPaint(6, Color.CYAN);
				renderer.setSeriesPaint(7, Color.YELLOW);
				renderer.setSeriesPaint(8, Color.MAGENTA);
				renderer.setSeriesPaint(9, Color.gray);
				

				// sets thickness for series (using strokes)
				renderer.setSeriesStroke(0, new BasicStroke(4.0f));
				renderer.setSeriesStroke(1, new BasicStroke(2.0f));
				renderer.setSeriesStroke(2, new BasicStroke(2.0f));
				renderer.setSeriesStroke(3, new BasicStroke(2.0f));
				renderer.setSeriesStroke(4, new BasicStroke(2.0f));
				renderer.setSeriesStroke(5, new BasicStroke(2.0f));
				renderer.setSeriesStroke(6, new BasicStroke(2.0f));
				renderer.setSeriesStroke(7, new BasicStroke(2.0f));
				renderer.setSeriesStroke(8, new BasicStroke(2.0f));
				renderer.setSeriesStroke(9, new BasicStroke(2.7f));

				plot.setRenderer(renderer);
				
				
				plot.setOutlinePaint(Color.BLACK);
				plot.setOutlineStroke(new BasicStroke(2.0f));
				
				
				
				
		
		
				try
				{
					//int width = 1504; /* Width of the image */
				  // int height = 900; /* Height of the image */ 
					
					int width = 650; /* Width of the image */
					int height = 400; /* Height of the image */ 
				   File lineChart = new File( "/home/cloud/workspace/ElasticCloud/ExponentialSmoothing.png" ); 
				  	System.out.println( lineChart.getAbsolutePath());
				   ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
				  
				}
				catch (IOException e){

				//System.out.println("Problem in creating chart.");
					e.printStackTrace();
				}
		
	}
	
	
	public static void Jchart3()
	{
		
	//	double[] xb = {15, 30, 45, 60, 75, 90,105,120,135,150,165,180,195,210,225,240,255,270,285,300,315, 330 /*, 345, 360, 375, 390, 405, 420, 435, 450, 465, 480*/ };
    //	double[] yb = { 20.8, 15.0, 12.8, 12.0, 13.0, 20.1, 26.1, 28.3, 30.5, 20.7, 14.3, 20.3, 22.5, 33.0, 35.7, 30.3, 28.7, 24.5, 22.0, 16.7, 14.3, 16.3 /*, 10.0, 11.3, 12.0, 14.3, 20.8, 19.1, 16.4, 11.4, 16.5, 13.0*/ };
    
		

		double[] xb = {210,225,240,255,270,285,300,315, 330, 345, 360, 375, 390, 405};
    	double[] yb = {100.0, 100.0, 23.9, 42.6, 37.0, 69.6, 20.8, 28.8, 4.0, 10.6, 4.1, 2.0, 6.4, 2.0};
    	
    	ExponentialSmoothing es=new ExponentialSmoothing();
		System.out.println(observedValues.toString());
		
    	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		for(int i=7; i<yb.length;i++)
		{
			line_chart_dataset.addValue(yb[i], "observed values" , new Integer((int)xb[i]));  
		}
	
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		MSEValues.removeAllElements();
		}
		for(int i=0; i<yb.length; i++)
		{
			observedValues.add(yb[i]);
		}
		
		for(int i=7; i<yb.length;i++)
		{
			line_chart_dataset.addValue(yb[i], "observed values" , new Integer((int)xb[i]));  
		}
		
		es.forecastObservedValues();
		
		System.out.println(forcastedValues.toString());
		
		makeMSEValues();
		
		System.out.println("MSR="+MSEValues.toString());
		
		double x=getBestAlpha();
		
		System.out.println(x);
		
		System.out.println("forcasted value= "+es.forecast (x));
		
		
		
		
		
		
	
		/*
		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		int x=-1;
		for(int i=0; i<observedValues.size();i++)
		{
		 x=i+1;
		line_chart_dataset.addValue( observedValues.get(i), "observed values" , new Integer(x));  
		}
		*/
		
		
		
		
		for(int i=0; i<forcastedValues.size(); i++)	
		{
			for(int j=7; j<forcastedValues.get(i).size(); j++)
			{
		//int n=j+1;
		line_chart_dataset.addValue( forcastedValues.get(i).get(j), "f(α="+alphaValues[i]+")" , new Integer((int)xb[j]));  
			}
		}
		
		

		JFreeChart lineChartObject = ChartFactory.createLineChart(
		  "","Time (Seconds)",
		  "CPU usage %",
		  line_chart_dataset,PlotOrientation.VERTICAL,
		  true,true,false);
		String path ="";
		
		//lineChartObject.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot=lineChartObject.getCategoryPlot();
		plot.setBackgroundPaint(Color.white);
		
		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		
		
		
		
		
		
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(1, Color.PINK);
		renderer.setSeriesPaint(3, Color.YELLOW);
		renderer.setSeriesPaint(4, Color.cyan);
		renderer.setSeriesPaint(5, Color.ORANGE);
		renderer.setSeriesPaint(6, Color.CYAN);
		renderer.setSeriesPaint(7, Color.BLUE);
		renderer.setSeriesPaint(8, Color.MAGENTA);
		renderer.setSeriesPaint(9, Color.YELLOW);
		

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		renderer.setSeriesStroke(4, new BasicStroke(2.0f));
		renderer.setSeriesStroke(5, new BasicStroke(2.0f));
		renderer.setSeriesStroke(6, new BasicStroke(2.0f));
		renderer.setSeriesStroke(7, new BasicStroke(3.1f));
		renderer.setSeriesStroke(8, new BasicStroke(2.0f));
		renderer.setSeriesStroke(9, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		
		
		plot.setOutlinePaint(Color.BLACK);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		
		try
		{
			int width = 650; /* Width of the image */
		   int height = 400; /* Height of the image */ 
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
