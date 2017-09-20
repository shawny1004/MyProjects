<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	return true;

}


</script>

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
              <label>Room Type</label>
              
              
              <select name="b1">
              <option value="1" <c:if test="${b1=='1' }">selected</c:if>>1</option>
              <option value="2" <c:if test="${b1=='2' }">selected</c:if>>2</option>
              <option value="3" <c:if test="${b1=='3' }">selected</c:if>>3</option>
              </select>
              Bedrooms
              <select name="b2">
              <option value="1" <c:if test="${b2=='1' }">selected</c:if>>1</option>
              <option value="2" <c:if test="${b2=='2' }">selected</c:if>>2</option>
              <option value="3" <c:if test="${b2=='3' }">selected</c:if>>3</option>
              </select>
              Bathrooms
              
            </p>
            
            <p>
              <label>Building Number</label>
              <input class="text-input small-input" type="text"  name="roomarea"  value="${bean.roomarea }" id="roomareaid"/>
              
            </p>
            
            <p>
              <label>Max Price</label>
              <input class="text-input small-input" type="text"  name="priceLimit"  value="${bean.priceLimit }" id="priceLimitid"/>
              
            </p>
           
            
            <p>
              <label>Smoking</label>
             <select name="smoke">
             <option value="Yes" <c:if test="${bean.smoke=='Yes' }">selected</c:if> >Yes</option>
             <option value="No" <c:if test="${bean.smoke=='No' }">selected</c:if> >No</option>
              </select>
              
            </p>
            
         
            <p>
              <label>Parking Lots</label>
             <select name="party">
             <option value="Yes" <c:if test="${bean.party=='Yes' }">selected</c:if> >Yes</option>
             <option value="No" <c:if test="${bean.party=='No' }">selected</c:if> >No</option>
              </select>
              
            </p>
            
            <p>
              <label>Pets</label>
             <select name="pet">
             <option value="Yes" <c:if test="${bean.pet=='Yes' }">selected</c:if> >Yes</option>
             <option value="No" <c:if test="${bean.pet=='No' }">selected</c:if> >No</option>
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
