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

import com.dao.MaintenanceDao;
import com.dao.UserDao;
import com.model.Maintenance;
import com.model.User;
import com.util.Pager;
import com.util.Util;

@Controller
@RequestMapping(value="maintenance")
public class MaintenanceController extends BaseController{



	
	
	@Resource(name="maintenanceDao")
	private MaintenanceDao maintenanceDao;

	
	@Resource(name="userDao")
	private UserDao userDao;
	
	//My MaintenanceList
	@RequestMapping(value="maintenancelist.do")
	public String maintenancelist(HttpServletRequest request,HttpServletResponse response) {
		
		String maintenanceType = request.getParameter("maintenanceType");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (maintenanceType != null && !"".equals(maintenanceType)) {
			sb.append(" maintenanceType like '%" + maintenanceType + "%' ");
			sb.append(" and ");

			request.setAttribute("maintenanceType", maintenanceType);
		}
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" deletestatus=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = maintenanceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Maintenance> list = maintenanceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "maintenance/maintenancelist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "maintenance/maintenancelist.do");
		request.setAttribute("url2", "maintenance/maintenance");
		request.setAttribute("title", "My Maintenance List");
		
		return "maintenance/maintenancelist";

	}

	

	//Redirect
	@RequestMapping(value="maintenanceadd.do")
	public String maintenanceadd(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("userlist", userDao.selectBeanlist(0, 9999, " where deletestatus=0 and role=2 and department='Maintenance'  "));

		request.setAttribute("url", "maintenance/maintenanceadd2.do");
		request.setAttribute("title", "Apply for Maintenance");
		return "maintenance/maintenanceadd";
	}
	

	//Apply for Maintenance
	@RequestMapping(value="maintenanceadd2.do")
	public void maintenanceadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String discription = request.getParameter("discription");
		String maintenanceType = request.getParameter("maintenanceType");
		String assignedEmpID = request.getParameter("assignedEmpID");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		Maintenance bean = new Maintenance();
		bean.setApplttime(Util.getTime3());
		bean.setAssignedEmp(userDao.selectBean(" where id= "+assignedEmpID));
		bean.setDiscription(discription);
		bean.setMaintenanceType(maintenanceType);

		
		bean.setStatus("waiting");
		bean.setUser(user);
		
		maintenanceDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='maintenancelist.do';</script>");
		
		
	}

	

	

	
	//Redirect
	@RequestMapping(value="maintenanceupdate3.do")
	public String maintenanceupdate3(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Maintenance Status");
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "maintenance/maintenanceupdate3";

	}
	
	
	
	//My Maintenance List
	@RequestMapping(value="maintenancelist2.do")
	public String maintenancelist2(HttpServletRequest request,HttpServletResponse response) {
		
		String maintenanceType = request.getParameter("maintenanceType");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (maintenanceType != null && !"".equals(maintenanceType)) {
			sb.append(" maintenanceType like '%" + maintenanceType + "%' ");
			sb.append(" and ");

			request.setAttribute("maintenanceType", maintenanceType);
		}
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		
		sb.append(" deletestatus=0  and assignedEmp.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = maintenanceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Maintenance> list = maintenanceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "maintenance/maintenancelist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		request.setAttribute("url", "maintenance/maintenancelist2.do");
		request.setAttribute("url2", "maintenance/maintenance");
		request.setAttribute("title", "My Maintenance List");
		
		return "maintenance/maintenancelist2";

	}
	
	
	
	
	//Redirect
	@RequestMapping(value="maintenanceupdate.do")
	public String maintenanceupdate(HttpServletRequest request,HttpServletResponse response) {
	
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		request.setAttribute("url", "maintenance/maintenanceupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Accept the Task");
		
		request.setAttribute("bean", bean);
		
		return "maintenance/maintenanceupdate";

	}
	
	

	//Accept 
	@RequestMapping(value="maintenanceupdate2.do")
	public void maintenanceupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
	
		String scheduledTIme = request.getParameter("scheduledTIme");
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setStatus("scheduled");
		bean.setScheduledTIme(scheduledTIme);
		
		maintenanceDao.updatebean(bean);
		

		writer.print("<script language='javascript'>alert('Successful');window.location.href='maintenancelist2.do';</script>");
	}
	
	
	
	//Maintenance Report List
	@RequestMapping(value="maintenancelist3.do")
	public String maintenancelist3(HttpServletRequest request,HttpServletResponse response) {
		
		String maintenanceType = request.getParameter("maintenanceType");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (maintenanceType != null && !"".equals(maintenanceType)) {
			sb.append(" maintenanceType like '%" + maintenanceType + "%' ");
			sb.append(" and ");

			request.setAttribute("maintenanceType", maintenanceType);
		}
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		
		
		sb.append(" deletestatus=0  and assignedEmp.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = maintenanceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Maintenance> list = maintenanceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "maintenance/maintenancelist3.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "maintenance/maintenancelist3.do");
		request.setAttribute("url2", "maintenance/maintenance");
		request.setAttribute("title", "Maintenance Report List");
		
		return "maintenance/maintenancelist3";

	}
	
	
	
	
	//Redirect
	@RequestMapping(value="maintenanceupdate5.do")
	public String maintenanceupdate5(HttpServletRequest request,HttpServletResponse response) {
	
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		request.setAttribute("url", "maintenance/maintenanceupdate6.do?id="+bean.getId());
		request.setAttribute("title", "Maintenance Report");
		
		request.setAttribute("bean", bean);
		
		return "maintenance/maintenanceupdate5";

	}
	
	

	//Maintenance Report
	@RequestMapping(value="maintenanceupdate6.do")
	public void maintenanceupdate6(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
	
		String report = request.getParameter("report");
		String status = request.getParameter("status");
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setReport(report);
		bean.setStatus(status);
		
		maintenanceDao.updatebean(bean);
		

		writer.print("<script language='javascript'>alert('Successful');window.location.href='maintenancelist3.do';</script>");
	}
	
	
	
	//Maintenance List
	@RequestMapping(value="maintenancelist4.do")
	public String maintenancelist4(HttpServletRequest request,HttpServletResponse response) {
		
		String maintenanceType = request.getParameter("maintenanceType");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (maintenanceType != null && !"".equals(maintenanceType)) {
			sb.append(" maintenanceType like '%" + maintenanceType + "%' ");
			sb.append(" and ");

			request.setAttribute("maintenanceType", maintenanceType);
		}
		
		
	
		
		sb.append(" deletestatus=0  and  status='waiting'  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = maintenanceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Maintenance> list = maintenanceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "maintenance/maintenancelist4.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "maintenance/maintenancelist4.do");
		request.setAttribute("url2", "maintenance/maintenance");
		request.setAttribute("title", "Maintenance List");
		
		return "maintenance/maintenancelist4";

	}
	
	
	
	
	//Redirect
	@RequestMapping(value="maintenanceupdate7.do")
	public String maintenanceupdate7(HttpServletRequest request,HttpServletResponse response) {
	
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		request.setAttribute("url", "maintenance/maintenanceupdate8.do?id="+bean.getId());
		request.setAttribute("title", "Accept");
		
		request.setAttribute("bean", bean);
		
		return "maintenance/maintenanceupdate7";

	}
	
	

	//Accept
	@RequestMapping(value="maintenanceupdate8.do")
	public void maintenanceupdate8(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
	
		String scheduledTIme = request.getParameter("scheduledTIme");
		
		Maintenance bean = maintenanceDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setStatus("scheduled");
		bean.setScheduledTIme(scheduledTIme);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		bean.setAssignedEmp(user);
		
		maintenanceDao.updatebean(bean);
		

		writer.print("<script language='javascript'>alert('Successful');window.location.href='maintenancelist2.do';</script>");
	}
	
	
	//Maintenance look up
	@RequestMapping(value="maintenancelist5.do")
	public String maintenancelist5(HttpServletRequest request,HttpServletResponse response ) {
		
		String maintenanceType = request.getParameter("maintenanceType");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (maintenanceType != null && !"".equals(maintenanceType)) {
			sb.append(" maintenanceType like '%" + maintenanceType + "%' ");
			sb.append(" and ");

			request.setAttribute("maintenanceType", maintenanceType);
		}
		
		
	
		
		sb.append(" deletestatus=0    order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = maintenanceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Maintenance> list = maintenanceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "maintenance/maintenancelist5.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "maintenance/maintenancelist5.do");
		request.setAttribute("url2", "maintenance/maintenance");
		request.setAttribute("title", "Maintenance Look Up");
		
		return "maintenance/maintenancelist5";

	}
}
