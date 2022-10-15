<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
    Document   : request
    Created on : 29 mai 2014, 10:54:17
    Author     : Raouia Boubadallah
--%>



<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@ page import="org.cloud.federation.utils.OVFUtil" %>
<%@ page import="org.cloud.federation.utils.VirtualMachine" %> 
<%@ page import="org.cloud.federation.utils.DB_Conversation" %>
<%@ page import="org.cloud.federation.utils.Conversation" %>
<%@ page import="org.cloud.federation.utils.Envelope" %>
<%@page import="org.cloud.federation.utils.Session"%>
<%@page import="org.cloud.federation.utils.AgentServlet"%>
<%@page language="java" session="true" %>


<%@page import="org.cloud.federation.agents.AnalyzeAgent"%>
<%@page import="java.io.File"%>
<%@page import="org.cloud.federation.utils.AppProductor"%>
<%@page import="org.cloud.federation.doa.*"%>
<%@page import="org.cloud.federation.model.*"%>



<%@page import="org.cloud.federation.utils.Converter"%>




   
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Template</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/login.css" />
<!-- CuFon: Enables smooth pretty custom font rendering. 100% SEO friendly. To disable, remove this section -->
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript" language="JavaScript" src="res/login.js"> </script>
<!-- CuFon ends -->

<script type="text/javascript">

function gestion_pass(){
var glo=document.getElementById('cadre').getElementsByTagName('input')[0].value;
var wsp=document.getElementById('cadre').getElementsByTagName('input')[1].value;
/* alert('le login est "'+glo+'" et le passe est "'+wsp+'",redirection vers RequesterP1.html')*/
/*document.getElementById('cadre').submit()*/
 
window.location.replace("ListInitiators.jsp?login="+glo+"&passworld="+wsp);

klog.quit();
}
</script>


<style type="text/css">

/* General styles */
body {
    margin: 0;
    padding: 0;
    font: 80%/1.5 Arial, Helvetica, sans-serif;
    color: #111;
    background-color: #FFF;
}



p#copyright {
    margin: 20px 10px;
    font-size: 90%;
    color: #999;
}

/* Form styles */
div.form-container {
    margin: 10px;
    padding: 5px;
    background-color: #FFF;
    border: #EEE 1px solid;
}

p.legend {
    margin-bottom: 1em;
}

p.legend em {
    color: #C00;
    font-style: normal;
}

div.errors {
    margin: 0 0 10px 0;
    padding: 5px 10px;
    border: #FC6 1px solid;
    background-color: #FFC;
}

div.errors p {
    margin: 0;
}

div.errors p em {
    color: #C00;
    font-style: normal;
    font-weight: bold;
}

div.form-container form p {
    margin: 0;
}

div.form-container form p.note {
    margin-left: 170px;
    font-size: 90%;
    color: #333;
}

div.form-container form fieldset {
    margin: 10px 0;
    padding: 10px;
    border: #DDD 1px solid;
}

div.form-container form legend {
    font-weight: bold;
    color: #666;
}

div.form-container form fieldset div {
    padding: 0.25em 0;
}

div.form-container label,div.form-container span.label {
    margin-right: 10px;
    padding-right: 10px;
    width: 150px;
    display: block;
    float: left;
    text-align: right;
    position: relative;
}

div.form-container label.error,div.form-container span.error {
    color: #C00;
}

div.form-container label em,div.form-container span.label em {
    position: absolute;
    right: 0;
    font-size: 120%;
    font-style: normal;
    color: #C00;
}

div.form-container input.error {
    border-color: #C00;
    background-color: #FEF;
}

div.form-container input:focus,div.form-container input.error:focus,div.form-container textarea:focus
    {
    background-color: #FFC;
    border-color: #FC6;
}

div.form-container div.controlset label,div.form-container div.controlset input
    {
    display: inline;
    float: none;
}

div.form-container div.controlset div {
    margin-left: 170px;
}

div.form-container div.buttonrow {
    margin-left: 180px;
}
</style>


<style type='text/css'>
root {
	display: block;
}

body {
	font: normal 11px auto 'Trebuchet MS', Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: #ffffff;
}

a {
	color: #c75f3e;
}

#mytable {
	width: 1024px;
	padding: 0;
	margin: 0;
	margin-left: 0px;
}

caption {
	padding: 0 0 5px 0;
	width: 1024px;
	font: italic 11px 'Trebuchet MS', Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th {
	font: bold 11px 'Trebuchet MS', Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: left;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA
		url(src/com/ttn/console/views/resources/bg_header.jpg) no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}

td.alt {
	background: #F5FAFA;
	color: #797268;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff url(src/com/ttn/console/views/resources/bullet1.gif)
		no-repeat;
	font: bold 10px 'Trebuchet MS', Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa url(src/com/ttn/console/views/resources/bullet2.gif)
		no-repeat;
	font: bold 10px 'Trebuchet MS', Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}

hr {
	border: 0;
	width: 80%;
	color: #C1DAD7;
	background-color: #C1DAD7;
	height: 2px;
}
</style>
</head>



<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="menu_nav">
        <ul>
          <li class="active"><a href=" "> </a></li>
          <!--   <li><a href="support.html">Support</a></li>  -->
          <!--    <li><a href="about.html">About Us</a></li> -->
          <!--   <li><a href="blog.html">receve information</a></li> -->
          <li><a href=" "> </a></li>
        </ul>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
      <div class="logo"><h1><a href="index.html">Cloud Federation <br /><small>IaaS Infrastructure Provider !!</small></a></h1></div>
    </div>
  </div>

  <div class="content">
    <div class="content_resize">
   
      <div class="mainbar">
        <div class="article">
        
        <!--  <p>Posted by <a href="#">Owner</a> | Filed under <a href="#">templates</a>, <a href="#">internet</a></p>  -->
          <img src="images/cloud.jpeg" width="605" height="250" alt="img" />
         <!--  <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo.</a> Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam. Cras fringilla magna. Phasellus suscipit, leo a pharetra condimentum, lorem tellus eleifend magna, eget fringilla velit magna id neque. Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam.</p>
          <p><a href="#" class="obg">Read more</a> <span>&nbsp;&bull;&nbsp;</span> <a href="#" class="obg">Comments (7)</a> | March 15, 2015</p>
        -->
        </div>
      
   
      
      
   
     
      
      
       <div class="content">

            <div id="wrapper">
          
                      
                   <%
                                                   	String NAME_PROVIDER="provider01";
                                                                 		String USER="user";
                                                                 		String button = request.getParameter("button");
                                                   			System.out.println("Button= "+button);	
                                                   			Vector<VirtualMachine> VMs_cfp=Session.VMs_cfp;
                                                                      
                                                                      File file ;
                                                                      boolean send =false;
                                                                         int maxFileSize = 5000 * 1024;
                                                                         int maxMemSize = 5000 * 1024;
                                                                         ServletContext context = pageContext.getServletContext();
                                                                         String filePath = context.getInitParameter("file-upload");
                                                                          String fileName= null;
                                                                          byte[]  bytes=null;
                                                                          String path= null;
                                                                       
                                                                           String ovf=null;
                                                                         // Verify the content type
                                                                         String contentType = request.getContentType();
                                                                         if(contentType !=null){
                                                                         if ((contentType.indexOf("multipart/form-data") >= 0)) {

                                                                            DiskFileItemFactory factory = new DiskFileItemFactory();
                                                                            
                                                                            // maximum size that will be stored in memory
                                                                            factory.setSizeThreshold(maxMemSize);
                                                                            // Location to save data that is larger than maxMemSize.
                                                                            factory.setRepository(new File("/home/cloud/Desktop/temp"));

                                                                            // Create a new file upload handler
                                                                            ServletFileUpload upload = new ServletFileUpload(factory);
                                                                            // maximum file size to be uploaded.
                                                                            upload.setSizeMax( maxFileSize );
                                                                            try{
                                                                               // Parse the request to get file items.
                                                                               List fileItems = upload.parseRequest(request);

                                                                               // Process the uploaded file items
                                                                               Iterator i = fileItems.iterator();

                                                                            
                                                                               while ( i.hasNext () )
                                                                               {
                                                                                  FileItem fi = (FileItem)i.next();
                                                                                  if ( !fi.isFormField () )   
                                                                                  {
                                                                                  // Get the uploaded file parameters
                                                                                  String fieldName = fi.getFieldName();
                                                                                  fileName = fi.getName();
                                                                                  boolean isInMemory = fi.isInMemory();
                                                                                  long sizeInBytes = fi.getSize();
                                                                                  // Write the file
                                                                                  if( fileName.lastIndexOf("\\") >= 0 ){
                                                                                  file = new File( filePath +
                                                                                  fileName.substring( fileName.lastIndexOf("\\"))) ;
                                                                                  }else{
                                                                                  file = new File( filePath +
                                                                                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                                                                                  }
                                                                                  fi.write( file ) ;
                                                                                 path= filePath +"/"+ fileName;
                                                                                System.out.println("path="+ path);
                                                                                ovf= Converter.fileToString(path);
                                                                                System.out.println("ovf="+ ovf);
                                                                                System.out.println("coco");
                                                                                AgentServlet.analyzeAgent.putO2AObject(ovf,false);
                                                                      				send=true;
                                                                                  }
                                                                               }
                                                                              
                                                                            }catch(Exception ex) {
                                                                               System.out.println(ex);
                                                                            }
                                                   %>
							<div class="content">
         				<div id="wrapper">
             			<h2>Request sent to provider.</h2> 
             			
         				</div>
     					</div>
							<%
								}
							                         
							                      }
							                     
							                      
							  						if(button ==null || button !=null && !button.equals("Send to Provider"))
							  						{
							%>
                    	  	<div class="content">
            					<div id="wrapper">
                				<font COLOR="red"><h2>Resources Discovery</h2> </font>
            					</div>
        						</div>
                    	 <form action="index.jsp" method="post" enctype="multipart/form-data">
                        <p class="legend">
                            <strong>Note:</strong> Required fields are marked with an
                            asterisk (<em>*</em>)
                        </p>

                        <fieldset>
                            <legend>Select an OVF file to upload:</legend>
                             <div>
                                <label for="fname"> OVF File Upload: <em>*</em></label>
                                <input type="file" name="fileOVF"  />
                            </div>
                            <br></br>
                            <div class="buttonrow">
                            <input type="submit" value="Upload OVF File And Send to Provider" class="button" />
                            <input type="button" value="Discard" class="button" />
                        </div> 
                        </fieldset>
                    </form>
                    	  
                    	  <%
                    	                      	  	}
                    	                      	                    
                    	                      	                        
                    	                      	    						if (button != null && !send ) {
                    	                      	  				if( button.equals("Send to Provider"))
                    	                      	  				{
                    	                      	  				 Vector<VirtualMachine> VMs=Session.VMs_cfp;
                    	                      	  					String app = request.getParameter("application");
                    	                      	  					String OS = request.getParameter("OS");
                    	                      	  					String CPU = request.getParameter("CPU");
                    	                      	  					String RAM = request.getParameter("RAM");
                    	                      	  					String disk= request.getParameter("Disk");
                    	                      	  					
                    	                      	  					VirtualMachine v=new VirtualMachine();
                    	                      	  					v.setCPU((double)Double.parseDouble(CPU));
                    	                      	  					v.setApplication(new AppProductor(app));
                    	                      	  					v.setRAM((int)Integer.parseInt(RAM));
                    	                      	  					v.setStorage((double)Double.parseDouble(disk));
                    	                      	  					v.setOS(OS);
                    	                      	  					VMs.addElement(v);
                    	                      	  					System.out.println("send to UserAgent");
                    	                      	  					Envelope e=new Envelope();
                    	                      	  					String s=NAME_PROVIDER+"_"+USER+Session.userId+"_"+Session.SessionId;
                    	                      	  					request.setAttribute("session", s);
                    	                      	  					System.out.println("Session="+request.getAttribute("session"));
                    	                      	  					e.setSession(s);
                    	                      	  					e.setBuild(USER+Session.userId);
                    	                      	  					e.setReceive(NAME_PROVIDER);
                    	                      	  					e.setVMs(VMs);
                    	                      	  					Session.userId=Session.userId+1;
                    	                      	  					Session.SessionId=Session.SessionId+1;
                    	                      	  					ovf=OVFUtil.listVirtualMachineToOvf_Cfp(e);
                    	                      	  					System.out.println("OVF FIle= "+ovf);
                    	                      	  					AgentServlet.analyzeAgent.putO2AObject(ovf,false);
                    	                      	  %>
							<div class="content">
            				<div id="wrapper">
                			<h2>Request sent to provider.</h2> 
                			<h3><a href="Proposal_OVF.jsp?session=<%=request.getAttribute("session") %>">Response of the request </a> </h3>
            				</div>
        					</div>
							<% 
							
						}
						}
					%>
						
							
							<% 
							
						
							
							if( button==null && !send )
							{
								if(Session.VMs_cfp!=null)
									{
										Session.VMs_cfp.removeAllElements();
									}
								%>
								<form action="Cfp_OVF.jsp" method="post">
					
							 	<div class="form-container">
								<table id='mytable' cellspacing='0'>
								<tr>
									<td scope='col'><h4>Virtual Machine:</h4></td>
									<td scope='col'><h4>Characteristics of virtual machines:</h4></td>
								</tr>
								
								<tr>
												<td scope='col'>VM1</td>
												<td scope='col'>
												<h3>HARDWARE</h3>
          										<p> <label for="email">CPU :</label><input  name="CPU" class="text" value="2"/> virtual CPU(s)</p>
          										<p> <label for="email">RAM :</label> <input  name="RAM" class="text" value="512"/> MB of memory</p>
          										<p> <label for="email">Disk :</label> <input  name="Disk" class="text" value="0.5"/> Gbyte Virtual Disk </p>
          										<hr></hr>
          										<h3>SOFTWARE</h3>
            									<p>	<label for="email">Operating System :</label><input  name="OS" class="text" value="CentOS-6.5 x86_64 / Linux"/></p>
            									<p> <label for="email">Application :</label> <input  name="application" class="text" value="Tomcat Webserver"/></p>	
            									<hr></hr>
          										<h3>Monitoring</h3>
          										<p>	<label for="email">Condition :</label><input  name="condition" class="text" value="cpuUsage > 90"/></p>
            									<p> <label for="email">Action :</label> <input  name="action" class="text" value="scale up"/></p>	
          										<input type="submit" value="Add new Rule" class="button" name="button" />
												</td>
								</tr>
								
							</table>
							</div>
							<br>
							<div align="center">
                            <input type="submit" value="Send to Provider" class="button" name="button" />
                            <input type="submit" value="Add Virtual Machine" class="button" name="button" />
                        	</div>       	
							</form>
							<% 
							}
							if (button != null && !send  ) {
							 if(button.equals("Add Virtual Machine"))
							{
								Vector VM=Session.VMs_cfp;
								System.out.println("VM= "+ VM);
								Vector<VirtualMachine> VMs=null;
								if(VM!=null)
								 VMs=  VM;
								else 
									 VMs=new Vector<VirtualMachine>();
								String app = request.getParameter("application");
								String OS = request.getParameter("OS");
								String CPU = request.getParameter("CPU");
								String RAM = request.getParameter("RAM");
								String disk= request.getParameter("Disk");
								
								VirtualMachine v=new VirtualMachine();
								v.setCPU((double)Double.parseDouble(CPU));
								v.setApplication(new AppProductor(app));
								v.setRAM((int)Integer.parseInt(RAM));
								v.setStorage((double)Double.parseDouble(disk));
								v.setOS(OS);
								VMs.addElement(v);
								VM=Session.VMs_cfp=VMs;
								
								
								int i;
							
								for(i=0; i<VMs.size(); i++)
								{
							%>
								<div class="content">
            					<div id="wrapper">
                				<font COLOR="red"><h2>Resources Discovery</h2> </font>
            					</div>
        						</div>
								<form action="Cfp_OVF.jsp" method="post">
							 	<div class="form-container">
								<table id='mytable' cellspacing='0'>
								<tr>
									<td scope='col'><h4>Virtual Machine:</h4></td>
									<td scope='col'><h4>Characteristics of virtual machines:</h4></td>
								</tr>
								
									
							<tr>
												<td scope='col'>VM<% out.print(i+1);%></td>
												<td scope='col'>
												<H3>HARDWARE</H3>
          										<P> <label for="email">CPU :</label><% out.print(VMs.get(i).getCPU()); %> virtual CPU(s)</P>
          										<P> <label for="email">RAM :</label> <% out.print(VMs.get(i).getRAM()); %> MB of memory</P>
          										<p> <label for="email">Disk :</label> <% out.print(VMs.get(i).getStorage()); %>Gbyte Virtual Disk  </p>
          										<hr></hr>
          										<H3>SOFTWARE</H3>
            									<P>	<label for="email">Operating System :</label><% out.print(VMs.get(i).getOS()); %></P>	
            									<P> <label for="email">Application :</label><% out.print(VMs.get(i).getApplication().getProduct()); %></P>
												</td>
								</tr>
							<%
							}
								%>
								<tr>
												<td scope='col'>VM<% out.print(i+1); %></td>
												<td scope='col'>
												<H3>HARDWARE</H3>
          										<P> <label for="email">CPU :</label><input  name="CPU" class="text" value=""/> virtual CPU(s)</P>
          										<P> <label for="email">RAM :</label> <input  name="RAM" class="text" value=""/> MB of memory</P>
          										<p> <label for="email">Disk :</label> <input  name="Disk" class="text" value=""/> Gbyte Virtual Disk </p>
          										<hr></hr>
          										<H3>SOFTWARE</H3>
            									<P>	<label for="email">Operating System :</label><input  name="OS" class="text" value=""/></P>
            									<P> <label for="email">Application :</label> <input  name="application" class="text" value=""/></P>	
												</td>
								</tr>
								
							</table>
							</div>
							<br>
							<div align="center">
                            <input type="submit" value="Send to Provider" class="button" name="button" />
                             <input type="submit" value="Add Virtual Machine" class="button" name="button" />
                        	</div>
                        	
							</form>
							<% 
							}
							 
							}
							%>
							<hr></hr>
								
          
                    
                    
                    </div>
                   
               
                </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
     </div>
     
     
      <div class="sidebar">
        <div class="gadget">
          <h2 class="star"><span> </span> Search IaaS resources </h2>
            <ul class="sb_menu">
            <li><a href="queryOVF.jsp" onclick='klog.logdial()'>Allocate resources with OVF standard</a></li>
            <li><a href="Cfp_OVF.jsp">Allocate resources </a></li>
            <li><a href="Proposal_OVF.jsp">Response of the request </a></li>
          </ul>
        </div>
        <div class="gadget">
          <h2 class="star"> Establish by </h2>
          <ul class="sb_menu">
            <li style="color:#0000ff">  Raouia Bouabdallah </li>
            <li style="color:#0000ff"> Soufiene Lajmi </li>
            <li style="color:#0000ff"> khalid ghadira </li>
          </ul>
        </div>
        <img src="images/soie.jpg" width="90" height="70" alt="img" />
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
       
      
       </div>
       </div>
       </div>
 


</body>
</html>
