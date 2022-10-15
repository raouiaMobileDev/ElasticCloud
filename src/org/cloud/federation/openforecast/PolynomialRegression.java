package org.cloud.federation.openforecast;


/******************************************************************************
 *  Compilation:  javac -cp .:jama.jar PolynomialRegression.java
 *  Execution:    java  -cp .:jama.jar PolynomialRegression
 *  Dependencies: jama.jar StdOut.java
 * 
 *  % java -cp .:jama.jar PolynomialRegression
 *  0.01 N^3 + -1.64 N^2 + 168.92 N + -2113.73 (R^2 = 0.997)
 *
 ******************************************************************************/

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

import Jama.Matrix;
import Jama.QRDecomposition;

/**
 *  The <tt>PolynomialRegression</tt> class performs a polynomial regression
 *  on an set of <em>N</em> data points (<em>y<sub>i</sub></em>, <em>x<sub>i</sub></em>).
 *  That is, it fits a polynomial
 *  <em>y</em> = &beta;<sub>0</sub> +  &beta;<sub>1</sub> <em>x</em> +
 *  &beta;<sub>2</sub> <em>x</em><sup>2</sup> + ... +
 *  &beta;<sub><em>d</em></sub> <em>x</em><sup><em>d</em></sup>
 *  (where <em>y</em> is the response variable, <em>x</em> is the predictor variable,
 *  and the &beta;<sub><em>i</em></sub> are the regression coefficients)
 *  that minimizes the sum of squared residuals of the multiple regression model.
 *  It also computes associated the coefficient of determination <em>R</em><sup>2</sup>.
 *  <p>
 *  This implementation performs a QR-decomposition of the underlying
 *  Vandermonde matrix, so it is not the fastest or most numerically
 *  stable way to perform the polynomial regression.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class PolynomialRegression {
    private final int N;                // number of observations
    private final String variableName;  // name of the predictor variable
    private int degree;                 // degree of the polynomial regression
    private Matrix beta;                // the polynomial regression coefficients
    private double SSE;                 // sum of squares due to error
    private double SST;                 // total sum of squares


  /**
     * Performs a polynomial reggression on the data points <tt>(y[i], x[i])</tt>.
     * Uses N as the name of the predictor variable.
      *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @param  degree the degree of the polynomial to fit
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public PolynomialRegression(double[] x, double[] y, int degree) {
        this(x, y, degree, "N");
    }
    

  /**
     * Performs a polynomial reggression on the data points <tt>(y[i], x[i])</tt>.
     *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @param  degree the degree of the polynomial to fit
     * @param  variableName the name of the predictor variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public PolynomialRegression(double[] x, double[] y, int degree, String variableName) {
        this.degree = degree;
        this.variableName = variableName;

        N = x.length;

        // in case Vandermonde matrix does not have full rank, reduce degree until it does
        boolean done = false;
        while (!done) {

            // build Vandermonde matrix
            double[][] vandermonde = new double[N][this.degree+1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= this.degree; j++) {
                    vandermonde[i][j] = Math.pow(x[i], j);
                }
            }
            Matrix X = new Matrix(vandermonde);

            // create matrix from vector
            Matrix Y = new Matrix(y, N);

            // find least squares solution
            QRDecomposition qr = new QRDecomposition(X);

            // decrease degree and try again
            if (!qr.isFullRank()) {
                this.degree--;
                continue;
            }

            // linear regression coefficients
            beta = qr.solve(Y);

            // mean of y[] values
            double sum = 0.0;
            for (int i = 0; i < N; i++)
                sum += y[i];
            double mean = sum / N;

            // total variation to be accounted for
            for (int i = 0; i < N; i++) {
                double dev = y[i] - mean;
                SST += dev*dev;
            }

            // variation not accounted for
            Matrix residuals = X.times(beta).minus(Y);
            SSE = residuals.norm2() * residuals.norm2();
            break;
        }
    }

   /**
     * Returns the <tt>j</tt>th regression coefficient.
     *
     * @param  j the index
     * @return the <tt>j</tt>th regression coefficient
     */
    public double beta(int j) {
        // to make -0.0 print as 0.0
      //  if (Math.abs(beta.get(j, 0)) < 1E-10) return 0.0;
        return beta.get(j, 0);
    }

   /**
     * Returns the degree of the polynomial to fit.
     *
     * @return the degree of the polynomial to fit
     */
    public int degree() {
        return degree;
    }

   /**
     * Returns the coefficient of determination <em>R</em><sup>2</sup>.
     *
     * @return the coefficient of determination <em>R</em><sup>2</sup>,
     *         which is a real number between 0 and 1
     */
    public double R2() {
     //   if (SST == 0.0) return 1.0;   // constant function
        return 1.0 - SSE/SST;
    }

   /**
     * Returns the expected response <tt>y</tt> given the value of the predictor
     *    variable <tt>x</tt>.
     *
     * @param  x the value of the predictor variable
     * @return the expected response <tt>y</tt> given the value of the predictor
     *         variable <tt>x</tt>
     */
    public double predict(double x) {
        // horner's method
        double y = 0.0;
        for (int j = degree; j >= 0; j--)
            y = beta(j) + (x * y);
        return y;
    }

   /**
     * Returns a string representation of the polynomial regression model.
     *
     * @return a string representation of the polynomial regression model,
     *         including the best-fit polynomial and the coefficient of
     *         determination <em>R</em><sup>2</sup>
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int j = degree;

        // ignoring leading zero coefficients
        while (j >= 0 && Math.abs(beta(j)) < 1E-10)
            j--;

        // create remaining terms
        while (j >= 0) {
            if      (j == 0) s.append(String.format("%.10f ", beta(j)));
            else if (j == 1) s.append(String.format("%.10f %s + ", beta(j), variableName));
            else             s.append(String.format("%.10f %s^%d + ", beta(j), variableName, j));
            j--;
        }
        s = s.append("  (R^2 = " + String.format("%.10f", R2()) + ")");
        return s.toString();
    }

   /**
     * Unit tests the <tt>PolynomialRegression</tt> data type.
     */
    public static void main(String[] args) {
    	
    	/*
        double[] x = { 80, 40, -40, -120, -200, -280 };
        double[] y = { 6.47, 6.24, 5.72, 5.09, 4.30, 3.33 };
        PolynomialRegression regression = new PolynomialRegression(x, y, 2);
        System.out.println(regression);
        System.out.println(regression.predict(70));
        */
    	
    	Jchart();	
    }
    
    
    
    
    public static void Jchart()
	{
    	
    	double[] xb = {15, 30, 45, 60, 75, 90,105,120,135,150,165,180,195 };
    	double[] yb = { 20.8, 15.0, 12.8, 12.0, 13.0, 20.1, 26.1, 28.3, 30.5, 20.7, 14.3, 20.3, 22.5};
    
		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

		for(int i=7; i<yb.length;i++)
		{
			line_chart_dataset.addValue(yb[i], "observed values" , new Integer((int)xb[i]));  
		}
		
		
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
    		System.out.println(xx);
    		System.out.println(yy);
    		PolynomialRegression regression = new PolynomialRegression(xx, yy, 2);
            double value=regression.predict(xb[i]);
            System.out.println("xb[i]= "+xb[i]);
            line_chart_dataset.addValue(value, "Polynomial Regression values" , new Integer((int) xb[i]));  
    	}
    			
		/*
		for(int i=0; i<forcastedValues.size(); i++)	
		{
			for(int j=0; j<forcastedValues.get(i).size(); j++)
			{
		int n=j+1;
		line_chart_dataset.addValue( forcastedValues.get(i).get(j), "f(α="+alphaValues[i]+")" , new Integer(n));  
			}
		}
		*/
		
		

		JFreeChart lineChartObject = ChartFactory.createLineChart(
		  "","The length of observations",
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
		
		
		
		
		/*
		
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(1, Color.PINK);
		renderer.setSeriesPaint(3, Color.YELLOW);
		renderer.setSeriesPaint(4, Color.cyan);
		renderer.setSeriesPaint(5, Color.ORANGE);
		renderer.setSeriesPaint(6, Color.CYAN);
		renderer.setSeriesPaint(7, Color.YELLOW);
		renderer.setSeriesPaint(8, Color.MAGENTA);
		renderer.setSeriesPaint(9, Color.BLUE);
		

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
		
		*/
		
		
		
		
		try
		{
			int width = 1504; /* Width of the image */
		   int height = 900; /* Height of the image */ 
		   File lineChart = new File( "/home/cloud/workspace/ElasticCloud/PolynomialRegression.png" ); 
		  	System.out.println( lineChart.getAbsolutePath());
		   ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
		  
		}
		catch (IOException e){

		//System.out.println("Problem in creating chart.");
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

