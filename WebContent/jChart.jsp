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
<%@ page import ="java.util.Date,java.util.Locale,java.text.SimpleDateFormat,java.text.DateFormat,java.text.ParseException"
 contentType="text/html; charset=utf-8" %>
 
<%
String query="select to_char(date_appel,'dd/mm/yyyy') as date ,nombre_rejet,duree_rejet,round(pr_nbr)||'%',round(pr_duree)||'%' from pop_stat_date_emm_bscs where code_type_trafic= 'MO'";
JDBCCategoryDataset dataset=new JDBCCategoryDataset("url","oracle.jdbc.driver.OracleDriver","user","pswd");
 
dataset.executeQuery(query);
JFreeChart chart = ChartFactory.createLineChart("nombre_rejet", "Id", "date",dataset, PlotOrientation.VERTICAL, true, true, false);
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
                ApplicationFrame f = new ApplicationFrame("Chart");
                f.setContentPane(chartPanel);
                f.pack();
                f.setVisible(true);
 
try
{
ChartUtilities.saveChartAsJPEG(new File("C:/chart.jpg"), chart, 400, 300);
}
catch (IOException e){

System.out.println("Problem in creating chart.");
}
%>