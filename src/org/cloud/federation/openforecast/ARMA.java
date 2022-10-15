package org.cloud.federation.openforecast;

import java.util.Vector;

public class ARMA {
	
	public static Vector<Double> observedValues= new Vector<Double>();
	public static Vector<Double>  forcastedValues= new Vector<Double>();
	public static double beta= 0.8;
	public static double alpha=0.15;

	
	
	public void ARMA()
	{
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		}
	}
	
	public static void clear()
	{
		if(observedValues.size()>0)
		{
		observedValues.removeAllElements();
		forcastedValues.removeAllElements();
		}
	}
	
	
	public static Vector<Double> forecast (Vector<Double> observedValues)
	{
		
		if ( forcastedValues.size()==0){
            forcastedValues.add(0.0);
            forcastedValues.add(0.0);
            forcastedValues.add(0.0);
		}  
        
        	for(int i=3; i<observedValues.size(); i++) {  	
           double forecast
                = (beta*observedValues.get(i-1))+alpha*(observedValues.get(i-2)+(1-(beta+alpha))*observedValues.get(i-2));
           forcastedValues.add(forecast);    
		}
		
		return forcastedValues;
	 }
	
	
	
	
	
	
	
	
	
	
	

}
