package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.DormsDao;
import com.dao.UserDao;
import com.model.Dorms;
import com.model.User;
import com.util.Pager;
import com.util.Util;

@Controller
@RequestMapping(value="user")
public class UserController extends BaseController{

	@Resource(name="userDao")
	private UserDao userDao;


	@Resource(name="dormsDao")
	private DormsDao dormsDao;


	@RequestMapping(value="/login.do")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username='" + username
				+ "' and password='" + password + "' and deletestatus=0");

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			Dorms dorms = dormsDao.selectBean(" where deletestatus=0 and advertisement=1 ");
			if(dorms!=null){
				session.setAttribute("dorms", dorms);
			}
			
			writer.print("<script language='javascript'>window.location.href='/UDS_SpringMVC/index.jsp';</script>");
			
		} else {
			writer.print("<script language='javascript'>alert('Username&Password does not match!');window.location.href='/UDS_SpringMVC/login.jsp';</script>");
		}

	}

	// quit
	@RequestMapping(value="loginout.do")
	public void loginout(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		session.removeAttribute("user");
		session.removeAttribute("dorms");
		writer
		.print("<script language='javascript'>alert('Logout successful!');window.location.href='/UDS_SpringMVC/login.jsp';</script>");
		
	}

	// change password
	@RequestMapping(value="passwordupdate.do")
	public String passwordupdate() {
		return "password";
	}
	
	@RequestMapping(value="passwordupdate2.do")
	public void passwordupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 	PrintWriter writer = this.getPrintWriter(response);

		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User bean = userDao.selectBean(" where username='" + user.getUsername()
				+ "' and password = '" + password1 + "' and deletestatus=0");
		if (bean != null) {
			bean.setPassword(password2);
			userDao.updatebean(bean);
			session.removeAttribute("user");
			writer
					.print("<script language='javascript'>alert('Change Successful, Please log in');window.location.href='/UDS_SpringMVC/login.jsp'</script>");
		} else {
			writer
					.print("<script language='javascript'>alert('Previous Password incorrect, Please Retype!');window.location.href='passwordupdate.do';</script>");
		}

	}

	


	//List
	@RequestMapping(value="userlist.do")
	public String userlist(HttpServletRequest request,HttpServletResponse response) {
		
		String username = request.getParameter("username");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {
			sb.append(" username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}

		sb.append(" deletestatus=0  and role=2 order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<User> list = userDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "user/userlist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "user/userlist.do");
		request.setAttribute("url2", "user/user");
		request.setAttribute("title", "Employee Management");
		
		return "user/userlist";

	}

	

	//Redirect
	@RequestMapping(value="useradd.do")
	public String useradd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "user/useradd2.do");
		request.setAttribute("title", "Add Staff");
		return "user/useradd";
	}
	

	//Add staff
	@RequestMapping(value="useradd2.do")
	public void useradd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String username = request.getParameter("username");
		String department = request.getParameter("department");
		String DOB = request.getParameter("DOB");
		String emailAddress = request.getParameter("emailAddress");
		String firstname = request.getParameter("firstname");
		String gender = request.getParameter("gender");
		String lastname = request.getParameter("lastname");
		String salaryRate = request.getParameter("salaryRate");
		
		
		User bean = userDao.selectBean(" where deletestatus=0 and username='"+username+"' ");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(Util.getTime3());
			bean.setDepartment(department);
			bean.setDOB(DOB);
			bean.setEmailAddress(emailAddress);
			bean.setFirstname(firstname);
			bean.setGender(gender);
			bean.setLastname(lastname);
			bean.setPassword("111111");
			bean.setRole(2);
			bean.setSalaryRate(salaryRate);
			bean.setUsername(username);
			userDao.insertbean(bean);
			writer.print("<script language='javascript'>alert('Successful');window.location.href='userlist.do';</script>");
		}else{
			writer.print("<script language='javascript'>alert('Failed,the username already exists');window.location.href='userlist.do';</script>");
		}
		
		
		
		
	}

	//Redirect
	@RequestMapping(value="userupdate.do")
	public String userupdate(HttpServletRequest request,HttpServletResponse response) {
		
		User bean = userDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "user/userupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Employee");
		
		return "user/userupdate";

	}

	//Edit employee
	@RequestMapping(value="userupdate2.do")
	public void userupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		String department = request.getParameter("department");
		String DOB = request.getParameter("DOB");
		String emailAddress = request.getParameter("emailAddress");
		String firstname = request.getParameter("firstname");
		String gender = request.getParameter("gender");
		String lastname = request.getParameter("lastname");
		String salaryRate = request.getParameter("salaryRate");
		
		User bean = userDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setDepartment(department);
		bean.setDOB(DOB);
		bean.setEmailAddress(emailAddress);
		bean.setFirstname(firstname);
		bean.setGender(gender);
		bean.setLastname(lastname);
		bean.setSalaryRate(salaryRate);

		
		userDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='userlist.do';</script>");
	}
	

	// Delete
	@RequestMapping(value="userdelete.do")
	public void userdelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		User bean = userDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='userlist.do';</script>");

	}

	
	//ReDirect
	@RequestMapping(value="userupdate3.do")
	public String userupdate3(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Employee");
		
		User bean = userDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "user/userupdate3";

	}
	
	
	//Sign Up
	@RequestMapping(value="register.do")
	public void register(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String username = request.getParameter("username");
		String DOB = request.getParameter("DOB");
		String emailAddress = request.getParameter("emailAddress");
		String firstname = request.getParameter("firstname");
		String gender = request.getParameter("gender");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password2");
		
		
		User bean = userDao.selectBean(" where deletestatus=0 and username='"+username+"' ");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(Util.getTime3());
			bean.setDOB(DOB);
			bean.setEmailAddress(emailAddress);
			bean.setFirstname(firstname);
			bean.setGender(gender);
			bean.setLastname(lastname);
			bean.setPassword("111111");
			bean.setRole(3);
			bean.setPassword(password);
			bean.setUsername(username);
			userDao.insertbean(bean);
			writer.print("<script language='javascript'>alert('Successful');window.location.href='login.jsp';</script>");
		}else{
			writer.print("<script language='javascript'>alert('Failed. Username Exists');window.location.href='register.jsp';</script>");
		}
		
		
	}
	
	//Personal Info
	@RequestMapping(value="userlist2.do")
	public String userlist2(HttpServletRequest request,HttpServletResponse response) {
		
		String username = request.getParameter("username");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {
			sb.append(" username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" deletestatus=0  and id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<User> list = userDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "user/userlist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "user/userlist2.do");
		request.setAttribute("url2", "user/user");
		request.setAttribute("title", "Personal Info Look Up");
		
		return "user/userlist2";

	}
	
	
	//Resident lookup
	@RequestMapping(value="userlist3.do")
	public String userlist3(HttpServletRequest request,HttpServletResponse response) {
		
		String username = request.getParameter("username");
		
		String dormsid = request.getParameter("dormsid");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {
			sb.append(" username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}

		sb.append(" deletestatus=0  and dorms.id="+dormsid+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<User> list = userDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "user/userlist3.do?dormsid="+dormsid, "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "user/userlist3.do?dormsid="+dormsid);
		request.setAttribute("url2", "user/user");
		request.setAttribute("title", "Resident Students");
		
		return "user/userlist3";

	}
}
