<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.jfree.chart.ChartFactory" %>
<%@ page import="org.jfree.chart.ChartUtilities" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.data.*" %>
<%@ page import="org.jfree.data.jdbc.JDBCCategoryDataset"%>
<%@ page import="org.jfree.chart.ChartPanel" %>
<%@ page import="org.jfree.ui.ApplicationFrame" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.jdom.*" %>
<%@ page import="org.jdom.input.*" %>
<%@ page import="org.jdom.filter.*" %>
<%@ page import="org.cloud.federation.utils.*" %>
<%@ page import="org.jfree.chart.plot.CategoryPlot" %>
<%@ page import="java.awt.Color" %>
<%@ page import="java.awt.BasicStroke" %>




<%@ page import ="java.util.Date,java.util.Locale,java.text.SimpleDateFormat,java.text.DateFormat,java.text.ParseException"
 contentType="text/html; charset=utf-8" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
 
<%
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

int second=0;
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
 //String second= date.substring(debut, debut+2);
 second=second+15;
line_chart_dataset.addValue( (double)Double.parseDouble(courant.getText()) , "CPU usage Vs time" , second+"");  
}

JFreeChart lineChartObject = ChartFactory.createLineChart(
  "","Time (seconds)",
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
		
		plot.setOutlinePaint(Color.BLACK);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		






try
{
	int width = 1500; /* Width of the image */
   int height = 900; /* Height of the image */ 
   File lineChart = new File( "/home/cloud/workspace/ElasticCloud/LineWL.png" ); 
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
    
%>
<html>

<body>
<img src="<%=path %>" width="640" height="480" alt="img" />
</body>


</html>