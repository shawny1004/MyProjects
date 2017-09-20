package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BusDao;
import com.model.Bus;
import com.util.Pager;

@Controller
@RequestMapping(value="bus")
public class BusController extends BaseController{
	
	@Resource(name="busDao")
	private BusDao busDao;

	
	//Bus List
	@RequestMapping(value="buslist.do")
	public String buslist(HttpServletRequest request,HttpServletResponse response) {
		
		String plateNumber = request.getParameter("plateNumber");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (plateNumber != null && !"".equals(plateNumber)) {
			sb.append(" plateNumber like '%" + plateNumber + "%' ");
			sb.append(" and ");

			request.setAttribute("plateNumber", plateNumber);
		}

		sb.append(" deletestatus=0  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = busDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Bus> list = busDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "bus/buslist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "bus/buslist.do");
		request.setAttribute("url2", "bus/bus");
		request.setAttribute("title", "Bus Management");
		
		return "bus/buslist";

	}

	

	//Redirect
	@RequestMapping(value="busadd.do")
	public String busadd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "bus/busadd2.do");
		request.setAttribute("title", "Add Bus");
		return "bus/busadd";
	}
	

	//Add bus
	@RequestMapping(value="busadd2.do")
	public void busadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String maintenanceTIme = request.getParameter("maintenanceTIme");
		String plateNumber = request.getParameter("plateNumber");
		
		
		
		Bus bean = new Bus();
		bean.setMaintenanceTIme(maintenanceTIme);
		bean.setPlateNumber(plateNumber);
		
		busDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='buslist.do';</script>");
		
		
		
		
	}

	//Redirect
	@RequestMapping(value="busupdate.do")
	public String busupdate(HttpServletRequest request,HttpServletResponse response) {
		
		
		Bus bean = busDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "bus/busupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Bus");
		
		return "bus/busupdate";

	}

	//Edit Bus
	@RequestMapping(value="busupdate2.do")
	public void busupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		String maintenanceTIme = request.getParameter("maintenanceTIme");
		String plateNumber = request.getParameter("plateNumber");
		
		Bus bean = busDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setMaintenanceTIme(maintenanceTIme);
		bean.setPlateNumber(plateNumber);

		
		busDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='buslist.do';</script>");
	}
	

	//Delete bus
	@RequestMapping(value="busdelete.do")
	public void busdelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Bus bean = busDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus(1);
		busDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='buslist.do';</script>");

	}

	
	//Redirect
	@RequestMapping(value="busupdate3.do")
	public String busupdate3(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Bus");
		
		Bus bean = busDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "bus/busupdate3";

	}
}
