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

import com.dao.LogsDao;
import com.model.Logs;
import com.model.User;
import com.util.Pager;
import com.util.Util;

@Controller
@RequestMapping(value="logs")
public class LogsController extends BaseController{


	@Resource(name="logsDao")
	private LogsDao logsDao;


	
	
	
	//Log List
	@RequestMapping(value="logslist.do")
	public String logslist(HttpServletRequest request,HttpServletResponse response) {
		
		String createtime = request.getParameter("createtime");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (createtime != null && !"".equals(createtime)) {
			sb.append(" createtime like '%" + createtime + "%' ");
			sb.append(" and ");

			request.setAttribute("createtime", createtime);
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
		long total = logsDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Logs> list = logsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "logs/logslist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "logs/logslist.do");
		request.setAttribute("url2", "logs/logs");
		request.setAttribute("title", "Log Management");
		
		return "logs/logslist";

	}

	

	//Redirect
	@RequestMapping(value="logsadd.do")
	public String logsadd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "logs/logsadd2.do");
		request.setAttribute("title", "Add Log");
		return "logs/logsadd";
	}
	

	//Add log
	@RequestMapping(value="logsadd2.do")
	public void logsadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String discription = request.getParameter("discription");
	
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
	
		
		
		
		Logs bean = new Logs();
		bean.setCreatetime(Util.getTime3());
		bean.setDiscription(discription);
		bean.setUser(user);
		bean.setRoute(user.getRoute());
		
		logsDao.insertbean(bean);
		
	
		
		writer.print("<script language='javascript'>alert('Successful');window.location.href='logslist.do';</script>");
		
		
		
		
	}

	//Redirect
	@RequestMapping(value="logsupdate.do")
	public String logsupdate(HttpServletRequest request,HttpServletResponse response) {
			
		Logs bean = logsDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "logs/logsupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Log");
		
		return "logs/logsupdate";

	}

	//edit log
	@RequestMapping(value="logsupdate2.do")
	public void logsupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		String discription = request.getParameter("discription");
		
		Logs bean = logsDao.selectBean(" where id = " + request.getParameter("id"));
		

		bean.setDiscription(discription);

		
		logsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='logslist.do';</script>");
	}
	

	// Delete log
	@RequestMapping(value="logsdelete.do")
	public void logsdelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Logs bean = logsDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus(1);
		logsDao.updatebean(bean);

		
		
		writer.print("<script language='javascript'>alert('Successful');window.location.href='logslist.do';</script>");

	}

	
	//Redirect
	@RequestMapping(value="logsupdate3.do")
	public String logsupdate3(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("title", "View Logs");
		
		Logs bean = logsDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "logs/logsupdate3";

	}
	
	
	//Log List
	@RequestMapping(value="logslist2.do")
	public String logslist2(HttpServletRequest request,HttpServletResponse response ) {
		
		String createtime = request.getParameter("createtime");
		String routeNumber = request.getParameter("routeNumber");
		String username = request.getParameter("username");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (createtime != null && !"".equals(createtime)) {
			sb.append(" createtime like '%" + createtime + "%' ");
			sb.append(" and ");

			request.setAttribute("createtime", createtime);
		}
		
		if (routeNumber != null && !"".equals(routeNumber)) {
			sb.append(" route.routeNumber like '%" + routeNumber + "%' ");
			sb.append(" and ");

			request.setAttribute("routeNumber", routeNumber);
		}
		
		if (username != null && !"".equals(username)) {
			sb.append(" user.username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		

		sb.append(" deletestatus=0  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = logsDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Logs> list = logsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "logs/logslist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "logs/logslist2.do");
		request.setAttribute("url2", "logs/logs");
		request.setAttribute("title", "Log Look Up");
		
		return "logs/logslist2";

	}
	
}
