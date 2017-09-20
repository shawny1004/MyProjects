<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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


</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  
  
  <%@ include file="../left.jsp" %>
  
  
  <div id="main-content">
    <!-- Main Content Section with everything -->
    
    <!-- Page Head -->
    
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>${title }</h3>
        
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          
         
            <div>
           <form action="${url }" method="post">
        Room Type：<input type="text" name="roomType" value="${roomType}" />   
               Building Number：<input type="text" name="dormNum" value="${dormNum}" />
        Room Number：<input type="text" name="roomNum" value="${roomNum}" /> 
            <input type="submit" value="Look Up" />
            </form>
            
            
              </div>
          
          
          <table>
            <thead>
              <tr>
               
                <th>Room Type</th>
                <th>Building Number</th>
                <th>Room Number</th>
                <th>Monthly Price</th>
                <th>Area</th>
                <th>Total Number of Room</th>
                <th>Rooms Occupied</th>
                <th>Rooms Available</th>
                <th>Option</th>
              </tr>
            </thead>
            
            <tbody>
            
            <c:forEach items="${list}" var="bean">
              <tr>
                <td>${bean.roomType }</td>
                <td>${bean.dormNum }</td>
                <td>${bean.roomNum}</td>
                <td>${bean.price}</td>
                <td>${bean.area}</td>
                <td>${bean.number_of_room}</td>
                <td>${bean.number_of_use}</td>
                <td>${bean.number_of_room-bean.number_of_use}</td>
               
                <td>
                 
                  <a href="${url2 }update3.do?id=${bean.id }" ><img src="resources/images/icons/pencil.png" />View</a> 
                 
                  <a href="applyadd.do?dormsid=${bean.id }" onclick=" return confirm('Are you sure to apply?'); "><img src="resources/images/icons/pencil.png" />Apply</a> 
                  
                  </td>
                 
              </tr>
              </c:forEach>
              
                
            <tfoot>
              <tr align="center" >
                <td colspan="15"  >
                  ${pageinfo}
                </td>
              </tr>
            </tfoot>
            
          </table>
        </div>
        
        
      
      </div>
 
    </div>
    
    
  
    

   
  </div>
  
</div>


</body>

</html>
