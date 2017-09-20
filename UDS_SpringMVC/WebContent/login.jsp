<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.util.Util"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Util.init(request);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Log In</title>
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

<script type="text/javascript" language="javascript">
function checkform(){
	
	 
	    if (document.getElementById('usernameid').value=="")
	{
		alert("Username Cannot be Empty!");
		return false;
	}
	
    if (document.getElementById('passwordid').value=="")
	{
		alert("Password Cannot be Empty!");
		return false;
	}
	
	
	return true;

}
function register(){
	
	 
	    window.location.href='register.jsp';

}

</script>

</head>
<body id="login">
<img style="width:1200px;height:420px; position:relative; left:19%" src="resources/images/dorm.jpg"  />
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
  
    
    <span style="font-size: 30px;font-weight: bold;color:#459300">University Dormitory System</span>
     </div>
  <!-- End #logn-top -->
  <div id="login-content">
  
     <form method="post"   action="user/login.do" onsubmit="return checkform()">
      
      <p>
        <label>Username：</label>
        <input class="text-input" type="text" name="username" id="usernameid"/>
      </p>
      <div class="clear"></div>
      <p>
        <label>Password：</label>
        <input class="text-input" type="password" name="password" id="passwordid"/>
      </p>
      <div class="clear"></div>
      <p>
        <label> 
         <input class="button" type="submit" value="Log in" />
     
        </label>
          <input class="button" type="button" value="Sign Up"  onclick="register()" />
      </p>
    </form>
   
  </div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
<br/>
<br/>
<br/>
<br/>

 <p style="color:#459300;font-size: 15px;font-weight:bold; text-align:center; text-decoration:underline">by Shuo Yang</p>
</body>
</html>
