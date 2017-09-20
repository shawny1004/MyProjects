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

import com.dao.BusDao;
import com.dao.RouteDao;
import com.dao.UserDao;
import com.model.Bus;
import com.model.Route;
import com.model.User;
import com.util.Pager;

@Controller
@RequestMapping(value="route")
public class RouteController extends BaseController{


	
	@Resource(name="routeDao")
	private RouteDao routeDao;

	@Resource(name="userDao")
	private UserDao userDao;
	
	
	@Resource(name="busDao")
	private BusDao busDao;
	
	
	//Route List
	@RequestMapping(value="routelist.do")
	public String routelist(HttpServletRequest request,HttpServletResponse response) {
		
		String routeNumber = request.getParameter("routeNumber");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (routeNumber != null && !"".equals(routeNumber)) {
			sb.append(" routeNumber like '%" + routeNumber + "%' ");
			sb.append(" and ");

			request.setAttribute("routeNumber", routeNumber);
		}

		sb.append(" deletestatus=0  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = routeDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Route> list = routeDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "route/routelist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "route/routelist.do");
		request.setAttribute("url2", "route/route");
		request.setAttribute("title", "Route Management");
		
		return "route/routelist";

	}

	

	//Redirect
	@RequestMapping(value="routeadd.do")
	public String routeadd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("userlist", userDao.selectBeanlist(0, 9999, " where deletestatus=0 and role=2 and department='Driver' and route is null "));
		request.setAttribute("buslist", busDao.selectBeanlist(0, 9999, " where deletestatus=0 and route is null "));
		request.setAttribute("url", "route/routeadd2.do");
		request.setAttribute("title", "Add Route");
		return "route/routeadd";
	}
	

	//Add route
	@RequestMapping(value="routeadd2.do")
	public void routeadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String busid = request.getParameter("busid");
		String driverid = request.getParameter("driverid");
		String gap = request.getParameter("gap");
		String routeChange = request.getParameter("routeChange");
		String routeNumber = request.getParameter("routeNumber");
		String runningTImeEnd = request.getParameter("runningTImeEnd");
		String runningTImeStart = request.getParameter("runningTImeStart");
		String discription = request.getParameter("discription");
		
		if(busid==null){
			writer.print("<script language='javascript'>alert('Please specify a bus');window.location.href='routeadd.do';</script>");
			return;
		}
		
		if(driverid==null){
			writer.print("<script language='javascript'>alert('Please specify a Driver');window.location.href='routeadd.do';</script>");
			return;
		}
		
		Bus bus = busDao.selectBean(" where id= "+busid);
		User driver = userDao.selectBean(" where id= "+driverid);
		
		Route bean = new Route();
		bean.setBus(bus);
		bean.setDriver(driver);
		bean.setGap(gap);
		bean.setRouteChange(routeChange);
		bean.setRouteNumber(routeNumber);
		bean.setRunningTImeEnd(runningTImeEnd);
		bean.setRunningTImeStart(runningTImeStart);
		bean.setDiscription(discription);
		
		routeDao.insertbean(bean);
		
		bus.setRoute(bean);
		busDao.updatebean(bus);
		
		driver.setRoute(bean);
		userDao.updatebean(driver);
		
		writer.print("<script language='javascript'>alert('Successful');window.location.href='routelist.do';</script>");
		
		
		
		
	}

	//Redirect
	@RequestMapping(value="routeupdate.do")
	public String routeupdate(HttpServletRequest request,HttpServletResponse response ) {
			
		Route bean = routeDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "route/routeupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Route");
		
		return "route/routeupdate";

	}

	//Edit
	@RequestMapping(value="routeupdate2.do")
	public void routeupdate2(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		String gap = request.getParameter("gap");
		String routeChange = request.getParameter("routeChange");
		String routeNumber = request.getParameter("routeNumber");
		String runningTImeEnd = request.getParameter("runningTImeEnd");
		String runningTImeStart = request.getParameter("runningTImeStart");
		String discription = request.getParameter("discription");
		
		Route bean = routeDao.selectBean(" where id = " + request.getParameter("id"));
		

		bean.setGap(gap);
		bean.setRouteChange(routeChange);
		bean.setRouteNumber(routeNumber);
		bean.setRunningTImeEnd(runningTImeEnd);
		bean.setRunningTImeStart(runningTImeStart);
		bean.setDiscription(discription);
		
		routeDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='routelist.do';</script>");
	}
	

	// Delete route
	@RequestMapping(value="routedelete.do")
	public void routedelete(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Route bean = routeDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus(1);
		routeDao.updatebean(bean);

		Bus bus = busDao.selectBean(" where id= "+bean.getBus().getId());
		User driver = userDao.selectBean(" where id= "+bean.getDriver().getId());
		
		
		
		bus.setRoute(null);
		busDao.updatebean(bus);
		
		driver.setRoute(null);
		userDao.updatebean(driver);
		
		
		writer.print("<script language='javascript'>alert('Successful');window.location.href='routelist.do';</script>");

	}

	
	//Redirect
	@RequestMapping(value="routeupdate3.do")
	public String routeupdate3(HttpServletRequest request,HttpServletResponse response ) {
		request.setAttribute("title", "View Route");
		
		Route bean = routeDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "route/routeupdate3";

	}
	
	
	//Route Look Up
	@RequestMapping(value="routelist2.do")
	public String routelist2(HttpServletRequest request,HttpServletResponse response ) {
		
		String routeNumber = request.getParameter("routeNumber");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (routeNumber != null && !"".equals(routeNumber)) {
			sb.append(" routeNumber like '%" + routeNumber + "%' ");
			sb.append(" and ");

			request.setAttribute("routeNumber", routeNumber);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" deletestatus=0 and driver.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = routeDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Route> list = routeDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "route/routelist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "route/routelist2.do");
		request.setAttribute("url2", "route/route");
		request.setAttribute("title", "Route Look Up");
		
		return "route/routelist2";

	}
	

	//Route List
	@RequestMapping(value="routelist3.do")
	public String routelist3(HttpServletRequest request,HttpServletResponse response) {
		
		String routeNumber = request.getParameter("routeNumber");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (routeNumber != null && !"".equals(routeNumber)) {
			sb.append(" routeNumber like '%" + routeNumber + "%' ");
			sb.append(" and ");

			request.setAttribute("routeNumber", routeNumber);
		}

		sb.append(" deletestatus=0  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = routeDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Route> list = routeDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "route/routelist3.do", "There are" + total + "Records");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "route/routelist3.do");
		request.setAttribute("url2", "route/route");
		request.setAttribute("title", "Route Lookup");
		
		return "route/routelist3";

	}
	
}
