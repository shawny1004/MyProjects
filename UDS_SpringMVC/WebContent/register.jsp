<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>University　Dormitory System</title>
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

<script language="javascript" type="text/javascript" src="js/showdate.js"></script>
 <script language="javascript" type="text/javascript">
 
 function checkform(){

	if (document.getElementById('usernameid').value=="")
	{
		alert("Username cannot be empty!");
		return false;
	}
	
	if(document.getElementById("password2id").value==""){
		
		alert('Password cannot be empty!');
		return false;
	}
	if(document.getElementById("password3id").value==""){
		
		alert('Password cannot be empty!');
		return false;
	}
	
	if(document.getElementById("password2id").value.length<6){
		
		alert('Password cannot be shorter than 6!');
		return false;
	}
	
	if(document.getElementById("password2id").value != document.getElementById("password3id").value){
		
		alert('Password does not match! ');
		return false;
	}
	
	
	
	if(document.getElementById("firstnameid").value==""){
		
		alert('Firstname cannot be empty!  ');
		return false;
	}
	if(document.getElementById("lastnameid").value==""){
		
		alert('Lastname cannot be empty!');
		return false;
	}
	if(document.getElementById("emailAddressid").value==""){
		
		alert('Email Addressid cannot be empty!');
		return false;
	}
	
	var reg = new RegExp('^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$');

	if(!reg.test(document.getElementById('emailAddressid').value)){
		alert("Please use correct email Address!");
		return false;
	}
	
	
	if(document.getElementById("telid").value==""){
		
		alert('Telephone Number cannot be empty! ');
		return false;
	}
	
	if (isNaN(document.getElementById("telid").value)) { 
　　　　alert("Telephone Number must be digits! "); 


　　　　return false;
　　} 
	
	
	return true;

}
 

  </script>
  

</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  
  

  
  
  <div id="main-content">
    
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Sign Up</h3>
        <ul class="content-box-tabs">
          
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        
        
        
      
        <div class="tab-content default-tab" id="tab1">
          <form action="user/register.do" method="post" onsubmit="return checkform()" >
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
           
            
             <p>
              <label>Username</label>
              <input class="text-input small-input" type="text" id="usernameid" name="username" />
              
            </p>
            
            
             <p>
              <label>Password</label>
              <input class="text-input small-input datepicker" type="password"  name="password2" id="password2id" />
            
            <p>
              <label>Repeat Password</label>
              <input class="text-input small-input" type="password" name="password3" id="password3id" />
            </p>
            
            <p>
              <label>First Name</label>
              <input class="text-input small-input" type="text" id="firstnameid" name="firstname" />
              
            </p>
            
            <p>
              <label>Last Name</label>
              <input class="text-input small-input" type="text" id="lastnameid" name="lastname" />
              
            </p>
            
            <p>
              <label>Gender</label>
              <select name="gender">
              <option value="male">Male</option>
              <option value="female">Female</option>
              </select>
              
            </p>
            
            <p>
              <label>Date of Birth</label>
             
              <INPUT    class="text-input small-input" onclick="javascript:fPopCalendar(DOBid,DOBid); return false;"  id="DOBid" name=DOB  readonly="readonly" /> 
            
            </p>
            
            <p>
              <label>Email Address</label>
              <input class="text-input small-input" type="text" id="emailAddressid" name="emailAddress" />
              
            </p>
            
             <p>
              <label>Telephone</label>
              <input class="text-input small-input" type="text" id="telid" name="tel" />
              
            </p>
              
            
            <p>
              <input class="button" type="submit" value="Submit" />
              
              &nbsp;&nbsp;&nbsp;
              
              <input type="button" value="Cancel"  onclick="javascript:history.go(-1)" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    
  
  </div>
  <!-- End #main-content -->
</div>


</body>

</html>
