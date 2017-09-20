<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><base href="<%=basePath%>">
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
<script type="text/javascript" language="javascript">
function checkform(){
	if(document.getElementById("usernameid").value==""){
		
		alert('Username Cannot be Empty');
		return false;
	}
	if(document.getElementById("firstnameid").value==""){
		
		alert('First Name Cannot be Empty');
		return false;
	}
	if(document.getElementById("lastnameid").value==""){
		
		alert('Last Name Cannot be Empty');
		return false;
	}
	if(document.getElementById("emailAddressid").value==""){
		
		alert('Email Address Cannot be Empty');
		return false;
	}
	
	var reg = new RegExp('^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$');

	if(!reg.test(document.getElementById('emailAddressid').value)){
		alert("Please input correct Email Address");
		return false;
	}
	
	if(document.getElementById("salaryRateid").value==""){
		
		alert('Salary Rate Cannot be Empty');
		return false;
	}
	
	
	
	var reg1 =  /^\d+$/;
	 
	if (document.getElementById('salaryRateid').value.match(reg1) == null)
	{
		alert("Salary Rate Must be an Integer");
		return false;
		
	}
	
	return true;

}

</script>

<script language="javascript" type="text/javascript" src="js/showdate.js"></script>

</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  
  
  <%@ include file="../left.jsp" %>
  
  
  <div id="main-content">
    
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>${title }</h3>
        <ul class="content-box-tabs">
          
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        
        
        
      
        <div class="tab-content default-tab" id="tab1">
          <form action="${url }" method="post" onsubmit="return checkform()">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            
            
            <p>
              <label>First Name</label>
              <input class="text-input small-input" type="text" id="firstnameid" name="firstname" value="${bean.firstname }" />
              
            </p>
            
            <p>
              <label>Last Name</label>
              <input class="text-input small-input" type="text" id="lastnameid" name="lastname"  value="${bean.lastname }"/>
              
            </p>
            
            <p>
              <label>Gender</label>
              <select name="gender">
              <option value="male" <c:if test="${bean.gender=='male' }">selected</c:if> >Male</option>
              <option value="female" <c:if test="${bean.gender=='female' }">selected</c:if>>Female</option>
              </select>
            </p>
            
            <p>
              <label>Date of Birth</label>

              <INPUT    class="text-input small-input" onclick="javascript:fPopCalendar(DOBid,DOBid); return false;"  id="DOBid" name=DOB  readonly="readonly" value="${bean.DOB }" /> 
               </p>
            
            <p>
              <label>Email Address</label>
              <input class="text-input small-input" type="text" id="emailAddressid" name="emailAddress"  value="${bean.emailAddress }"/>
              
            </p>
            
            <p>
              <label>Salary Rate</label>
              <input class="text-input small-input" type="text" id="salaryRateid" name="salaryRate"  value="${bean.salaryRate }"/>
              
            </p>
            
            <p>
              <label>Department</label>
             <select name="department">
         
             <option value="Maintenance" <c:if test="${bean.department=='Maintenance' }">selected</c:if>>Maintenance</option>
             <option value="Driver" <c:if test="${bean.department=='Driver' }">selected</c:if>>Driver</option>
             </select>
              
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
