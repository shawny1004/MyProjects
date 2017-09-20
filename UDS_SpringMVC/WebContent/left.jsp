<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
        org.springframework.web.context.WebApplicationContext app = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

        com.dao.MessageDao messageDao = (com.dao.MessageDao) app.getBean("messageDao");

		com.model.User user = (com.model.User)session.getAttribute("user");
		
		long count = messageDao.selectBeanCount(" where  deletestatus2=0  and status='Unread' and touser.id="+user.getId());
        
        
		com.dao.ApplyDao applyDao = (com.dao.ApplyDao) app.getBean("applyDao");
		
		long count2 = applyDao.selectBeanCount(" where  deletestatus=0  and status='Not_reviewed' ");
		
 %>

<div id="sidebar">
    <div id="sidebar-wrapper">
     <br/>
      
      <a href="#">
      <span style="font-size: 30px;font-weight: bold;">Menu</span>
      </a>
     
      
      <ul id="main-nav">
        
        
        <c:if test="${user.role==1}">
        
         <li> <a href="#" class="nav-top-item">Employee Management</a>
          <ul>
            <li><a href="user/userlist.do">Employee List</a></li>
            <li><a href="user/useradd.do">Add Employee</a></li>
        
          </ul>
        </li>
        
        
         <li> <a href="#" class="nav-top-item">Dorm Management</a>
          <ul>
            <li><a href="dorm/dormslist.do">Dorm List</a></li>
            <li><a href="dorm/dormsadd.do">Add Dorm</a></li>
            <li><a href="dorm/dormslist3.do">Advertisements</a></li>
            <li><a href="dorm/dormslist4.do">Living Students</a></li>
          </ul>
        </li>
        
       
        <%
        
        if(count2>0){
        %>
        
        <li> <a href="#" class="nav-top-item"><span style="color: yellow;">Dorm Application(<%=count2 %>)</span></a>
          <ul>
            <li><a href="apply/applylist2.do">Dorm Applications</a></li>
            <li><a href="apply/applylist3.do">Application Status</a></li>
          </ul>
        </li>
        <%
        }else{
        %>	
         <li> <a href="#" class="nav-top-item">Dorm Application</a>
          <ul>
            <li><a href="apply/applylist2.do">Dorm Applications</a></li>
            <li><a href="apply/applylist3.do">Application Status</a></li>
          </ul>
        </li>
        
         <% 	
        }
        %>
        
        <%
        
        if(count>0){
        %>
        <li> <a href="#" class="nav-top-item"><span style="color: yellow;">Message(<%=count %>)</span></a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd7.do">New Message</a></li>

          </ul>
        </li>
        <%
        }else{
        %>	
        <li> <a href="#" class="nav-top-item">Message</a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd7.do">New Message</a></li>

          </ul>
        </li>
        <% 	
        }
        %>
        
        
         <li> <a href="#" class="nav-top-item">Maintenance</a>
          <ul>
            <li><a href="maintenance/maintenancelist5.do">Maintenance List</a></li>
           
        
          </ul>
        </li>
        
        
        <li> <a href="#" class="nav-top-item">Bus</a>
          <ul>
            <li><a href="bus/buslist.do">Bus Management</a></li>
           
        <li><a href="route/routelist.do">Routes</a></li>
          </ul>
        </li>
        
        
         <li> <a href="#" class="nav-top-item">Bus Log</a>
          <ul>
            <li><a href="logs/logslist2.do">Log List</a></li>
           
        
          </ul>
        </li>
        
        
      </c:if>  
      
      
      
      <c:if test="${user.role==3}">
        
         <li> <a href="#" class="nav-top-item">Preference</a>
          <ul>
            <li><a href="preference/preferencelist.do">My preferences</a></li>
           
        
          </ul>
        </li>
        
        
        <li> <a href="#" class="nav-top-item">Dormitory</a>
          <ul>
            <li><a href="dorm/dormslist2.do">Dorm Lookup</a></li>
           
           	<li><a href="apply/applylist.do">Application Status</a></li>
        
          </ul>
        </li>
        
        
         <li> <a href="#" class="nav-top-item">Find Roommates</a>
          <ul>
            <li><a href="preference/preferencelist2.do">Roommate Lookup</a></li>
           
        
          </ul>
        </li>
        
       
        
        
         <%
        
        if(count>0){
        %>
         <li> <a href="#" class="nav-top-item"><span style="color: yellow;">Message(<%=count %>)</span></a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        
        
        <%
        }else{
        %>	
        <li> <a href="#" class="nav-top-item">Message</a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        <% 	
        }
        %>
        
        
        <li> <a href="#" class="nav-top-item">Maintenance</a>
          <ul>
           <li><a href="maintenance/maintenanceadd.do">Apply for Maintenance</a></li>
        	<li><a href="maintenance/maintenancelist.do">Maintenance Status</a></li>
          </ul>
        </li>
        
        <li> <a href="#" class="nav-top-item">Bus</a>
          <ul>
            <li><a href="route/routelist3.do">Route Lookup</a></li>
           
        
          </ul>
        </li>
       
      </c:if>  
      
      
      
      <c:if test="${user.role==2}">
        
        <c:if test="${user.department=='Maintenance'}">
        
         <li> <a href="#" class="nav-top-item">My Maintenance</a>
          <ul>
            <li><a href="maintenance/maintenancelist2.do">Maintenance List</a></li>
           
        
          </ul>
        </li>
        
        <li> <a href="#" class="nav-top-item">Reports</a>
          <ul>
            <li><a href="maintenance/maintenancelist3.do">Report List</a></li>
           
        
          </ul>
        </li>
        
        <li> <a href="#" class="nav-top-item">Tasks</a>
          <ul>
            <li><a href="maintenance/maintenancelist4.do">Task Lookup</a></li>
           
        
          </ul>
        </li>
        
        
        <%
        
        if(count>0){
        %>
         <li> <a href="#" class="nav-top-item"><span style="color: yellow;">Message(<%=count %>)</span></a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        
        
        <%
        }else{
        %>	
        <li> <a href="#" class="nav-top-item">Message</a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        <% 	
        }
        %>
        
        <li> <a href="#" class="nav-top-item">Personal Info</a>
          <ul>
            <li><a href="user/userlist2.do">Information</a></li>
           
        
          </ul>
        
       </li>
      </c:if>  
      
      <c:if test="${user.department=='Driver'}">
        
         <li> <a href="#" class="nav-top-item">Route</a>
          <ul>
            <li><a href="route/routelist2.do">Route Lookup</a></li>
           
        
          </ul>
        </li>
        
       
         <li> <a href="#" class="nav-top-item">Log</a>
          <ul>
            <li><a href="logs/logslist.do">Log List</a></li>
           
        
          </ul>
        </li>
        
        
         <li> <a href="#" class="nav-top-item">Position</a>
          <ul>
            <li><a href="position/positionlist.jsp">Report Position </a></li>
           
        
          </ul>
        </li>
        
       <%
        
        if(count>0){
        %>
         <li> <a href="#" class="nav-top-item"><span style="color: yellow;">Message(<%=count %>)</span></a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        
        
        <%
        }else{
        %>	
        <li> <a href="#" class="nav-top-item">Message</a>
          <ul>
            <li><a href="message/messagelist.do">Sent Box</a></li>
           
        	<li><a href="message/messagelist2.do">Inbox</a></li>
        	
        	<li><a href="message/messageadd5.do">Send Message to Admin</a></li>
          </ul>
        </li>
        <% 	
        }
        %>
        
        
        <li> <a href="#" class="nav-top-item">Personal Info</a>
          <ul>
            <li><a href="user/userlist2.do">Information</a></li>
           
        
          </ul>
        
       </li>
      </c:if>  
      
        </c:if>  
        
        <li> <a href="#" class="nav-top-item">Password</a>
          <ul>
            <li><a href="user/passwordupdate.do">Change Password</a></li>
           
          </ul>
        </li>
        
         <li> <a href="#" class="nav-top-item">Log out</a>
          <ul>
            <li><a href="user/loginout.do">Log out</a></li>
           
          </ul>
        </li>
      </ul>
    </div>
  </div>

  
 
  
  
  
  <!-- End #sidebar -->