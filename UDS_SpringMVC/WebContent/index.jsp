<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>University Dormitory System</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

</head>
<body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="sidebar">
    
     <%@ include file="left.jsp" %>
  </div>
 

  <div id="main-content">
   
    
    <!-- Page Head -->
    <h2>Welcome back ${user.username }</h2>
    
    
    
   <c:if test="${user.role==3}">
   <div align="left" style="font-size: 25px;">
   <br/>
  Advertisement &nbsp;&nbsp;&nbsp;&nbsp; <tr/>
  <tr/><br/><br/>
  On Sale(10% price off):
  <br/>
  Room Type:${dorms.roomType }&nbsp;&nbsp;<br/> Building Number:${dorms.dormNum }&nbsp;&nbsp;<br/> Room Number:${dorms.roomNum }<br/> Room Price:${dorms.price}
  <br/>
   </div>  
     <img src="<%=basePath %>uploadfile/${dorms.image }" position:relative; left:19%;width="640" height="480"/> 
     <p style="font-size:15px;">Please contact Admin for detail!</p>
   </c:if>
    
    
    
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>

