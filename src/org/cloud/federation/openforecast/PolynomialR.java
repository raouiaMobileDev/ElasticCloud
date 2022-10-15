package org.cloud.federation.openforecast;

import java.util.Vector;

public class PolynomialR {
	
	
	public static Vector<Double> x= new Vector<Double>();
	public static Vector<Double> y= new Vector<Double>();
	public static Vector<Double> matrice2= new Vector<Double>();
	public static Vector<Vector<Double>> matrice=new Vector<Vector<Double>> ();
	public static int n=x.size();
	//public static Vector<Vector<Double>>  forcastedValues= new Vector<Vector<Double>>();
	
	/*
	public static Vector<Vector<Double>> matricePR2( Vector<Double> x)
	{
		Vector<Vector<Double>> matrice=new Vector<Vector<Double>> ();
		return matrice;
	}
	*/
	public static Double sumX (Vector<Double> x)
	{
		double s=0;
		
		for(int i=0; i<x.size(); i++)
		{
			s+=x.get(i);
		}	
		return s;
	}
	
	
	public static Double sumXSquare (Vector<Double> x, int power)
	{
		double s=0;
		
		for(int i=0; i<x.size(); i++)
		{
			s+=Math.pow(x.get(i), power);
		}	
		return s;
	}
	
	public static Double sumXYSquare (Vector<Double> x,Vector<Double> y)
	{
		double s=0;
		
		for(int i=0; i<x.size(); i++)
		{
			s+=(x.get(i)*Math.pow(y.get(i),2));
		}	
		return s;
	}
	
	public static Double sumXY(Vector<Double> x,Vector<Double> y)
	{
		double s=0;
		
		for(int i=0; i<x.size(); i++)
		{
			s+=(x.get(i)*y.get(i));
		}	
		return s;
	}
	
	

	
	public static Vector<Vector<Double>> fullMatric()
	{
		matrice.clear();
		int n= x.size();
		double x1=sumX(x);
		double x2=sumXSquare(x, 2);
		double x3=sumXSquare(x, 3);
		double x4=sumXSquare(x, 4);
		
		Vector<Double> a1=new Vector<Double> ();
		a1.add((double)n);
		a1.add(x1);
		a1.add(x2);
		
		Vector<Double> a2=new Vector<Double> ();
		a2.add(x1);
		a2.add(x2);
		a2.add(x3);
		
		Vector<Double> a3=new Vector<Double> ();
		a3.add(x2);
		a3.add(x3);
		a3.add(x4);
		
		matrice.add(a1);
		matrice.add(a2);
		matrice.add(a3);
		
		return matrice;
	}
	
	public static Vector<Double> fullmatrice2 ()
	{
		matrice2.clear();
		double x1=sumX(x);
		double xy=sumXY(x,y);
		double xy2=sumXYSquare(x,y); 
		
		matrice2.add(x1);
		matrice2.add(xy);
		matrice2.add(xy2);
		return matrice2;
	}
	
	
	public static Double a2 (Vector<Double> matrice2, Vector<Vector<Double>> matrice)
	{
		
		double x1= matrice.get(0).get(0);
		double x2= matrice.get(0).get(1);		
		double x3= matrice.get(0).get(2);		
		double x4= matrice.get(1).get(0);
		double x5= matrice.get(1).get(1);
		double x6= matrice.get(1).get(2);
		double x7= matrice.get(2).get(0);
		double x8= matrice.get(2).get(1);
		double x9= matrice.get(2).get(2);
				
		double y1= matrice2.get(0);		
		double y2= matrice2.get(1);	
		double y3= matrice2.get(2);		
				
				
		Double v1=-(x1*y3/x7);
		Double v2= (x1*x8*y2)/((x5*x7)+(x4*Math.pow(x5, 2)*y3));
		Double v3= -(x2*y2*x7)/(x7+(x4*x5*y3));
			
		Double p1=(x1*x8*x4*x5*x9)/(Math.pow(x7, 2)+(x4*x5*x7*y3));
		Double p2=-(x1*x8*x6)/((x5*x7)+(x4*Math.pow(x5, 2)*y3));
		Double p3=-(x1*x9)/x7;
		Double p4=(x2*x4*x5*x9)/(x7+(x4*x5*y3));
		
		Double a=(v1+v2+v3+y1)/(p1+p2+p3+p4-x3)	;
		
		return a;
	}
	
	
	
	public static Double a0 (Vector<Double> matrice2, Vector<Vector<Double>> matrice,Double a2)
	{
		
		double x1= matrice.get(0).get(0);
		double x2= matrice.get(0).get(1);		
		double x3= matrice.get(0).get(2);		
		double x4= matrice.get(1).get(0);
		double x5= matrice.get(1).get(1);
		double x6= matrice.get(1).get(2);
		double x7= matrice.get(2).get(0);
		double x8= matrice.get(2).get(1);
		double x9= matrice.get(2).get(2);
				
		double y1= matrice2.get(0);		
		double y2= matrice2.get(1);	
		double y3= matrice2.get(2);		
				
				
		Double v1=-(x7*y1)/x1;
		Double v2=-(x7*x2*x6*a2)/(x1*x5);
		Double v3= (x7*x2*y2)/x1;
		Double v4= (x7*x3*a2)/x1;
		Double v5= (x8*x4)/x5;
		Double v6= (x8*x6*a2)/x5;
		Double v7= -(x8*y2)/x5;
		Double v8= -(x9*a2)+y3;
			
		Double p1= (x7*x2*x4)/(x1*x5);
	
		
		Double a0=(v1+v2+v3+v4+v5+v6+v7+v8)/p1;
		
		return a0;
	}
	
	
	public static void main (String args[])
	{
		x.add((double) 80);
		x.add((double) 40);
		x.add((double) -40);
		x.add((double) -120);
		x.add((double) -200);
		x.add((double) -280);
		
		y.add((double) 6.47);
		y.add((double) 6.24);
		y.add((double) 5.72);
		y.add((double) 5.09);
		y.add((double) 4.30);
		y.add((double) 3.33);
		
		
		Vector<Double> matrice2 =fullmatrice2 ();
		Vector<Vector<Double>>  matrice=fullMatric();
		Double a2= a2(matrice2, matrice);
		System.out.println("a2="+ a2);
		
		Double a0= a0(matrice2, matrice,a2);
		System.out.println("a0="+ a0);
	}

}
