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

import com.dao.ApplyDao;
import com.dao.DormsDao;
import com.dao.UserDao;
import com.model.Apply;
import com.model.Dorms;
import com.model.User;
import com.util.Pager;
import com.util.Util;
@Controller
@RequestMapping(value="apply")
public class ApplyController extends BaseController{


	


	@Resource(name="applyDao")
	private ApplyDao applyDao;


	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dormsDao")
	private DormsDao dormsDao;
	
	//My application list
	@RequestMapping(value="applylist.do")
	public String applylist(HttpServletRequest request,HttpServletResponse response) {
		
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dorms.dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" dorms.roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
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
		long total = applyDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Apply> list = applyDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "apply/applylist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "apply/applylist.do");
		request.setAttribute("url2", "apply/apply");
		request.setAttribute("title", "My application List");
		
		return "apply/applylist";

	}

	

	//redirect
	@RequestMapping(value="applyadd.do")
	public String applyadd(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter writer = this.getPrintWriter(response);
		
		String dormsid = request.getParameter("dormsid");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		//check if student already has a dorm
		User uu = userDao.selectBean(" where id= "+user.getId());
		if(uu.getDorms()!=null){
			writer.print("<script language='javascript'>alert('Failed. You already have a dorm!');window.location.href='dormslist2.do';</script>");
			return null;
		}
		
		//check if the students applied before
		
		Apply apply = applyDao.selectBean(" where deletestatus=0 and user.id="+user.getId()+" and status='Not_reviewed' and dorms.id="+dormsid);
		if(apply!=null){
			writer.print("<script language='javascript'>alert('Failed.  You have already applied for this dorm!');window.location.href='dormslist2.do';</script>");
			return null;
		}
		
		
		
		request.setAttribute("url", "apply/applyadd2.do?dormsid="+dormsid);
		request.setAttribute("title", "Apply Dorm");
		return "apply/applyadd";
	}
	

	//Dorm Application
	@RequestMapping(value="applyadd2.do")
	public void applyadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String dormsid = request.getParameter("dormsid");
		String applyExplain = request.getParameter("applyExplain");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		Apply bean = new Apply();
		bean.setApplytime(Util.getTime3());
		bean.setDorms(dormsDao.selectBean(" where id= "+dormsid));
		bean.setApplyExplain(applyExplain);
		bean.setStatus("Not_reviewed");
		bean.setUser(user);
		
		applyDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='applylist.do';</script>");
		
		
	}

	

	//Cancel Application
	@RequestMapping(value="applydelete.do")
	public void applydelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		Apply bean = applyDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setStatus("Cancel_application");
		
		applyDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='applylist.do';</script>");

	}

	
	//redirect
	@RequestMapping(value="applyupdate3.do")
	public String applyupdate3(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Application Status");
		
		Apply bean = applyDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "apply/applyupdate3";

	}
	
	
	//Dorm Application List
	@RequestMapping(value="applylist2.do")
	public String applylist2(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {
			sb.append(" username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dorms.dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" dorms.roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}
		
		
		sb.append(" deletestatus=0  and status='Not_reviewed' order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = applyDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Apply> list = applyDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "applylist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "apply/applylist2.do");
		request.setAttribute("url2", "apply/apply");
		request.setAttribute("title", "Dorm Application List");
		
		return "apply/applylist2";

	}
	
	
	
	
	//redirect
	@RequestMapping(value="applyupdate.do")
	public String applyupdate(HttpServletRequest request,HttpServletResponse response) {
	
		
		Apply bean = applyDao.selectBean(" where id = " + request.getParameter("id"));
		
		request.setAttribute("url", "apply/applyupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Application Review");
		
		request.setAttribute("bean", bean);
		
		return "apply/applyupdate";

	}
	
	

	//Check Application
	@RequestMapping(value="applyupdate2.do")
	public void applyupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		String status = request.getParameter("status");
		String applyReply = request.getParameter("applyReply");
		
		Apply bean = applyDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setStatus(status);
		bean.setApplyReply(applyReply);
		applyDao.updatebean(bean);
		
		
		
		if("Passed".equals(status)){
			
			Dorms dorms = dormsDao.selectBean(" where id= "+bean.getDorms().getId());
			
			if(dorms.getNumber_of_room()-dorms.getNumber_of_use()<=0){
				bean.setStatus("Failed");
				bean.setApplyReply("Not Enough Room");
				applyDao.updatebean(bean);
			}else{
				User user = userDao.selectBean(" where id= "+bean.getUser().getId());
				user.setDorms(dorms);
				user.setChecktime(Util.getTime3());
				userDao.updatebean(user);
				dorms.setNumber_of_use(dorms.getNumber_of_use()+1);
				dormsDao.updatebean(dorms);
				
			}
		}
		

		writer.print("<script language='javascript'>alert('Successful');window.location.href='applylist2.do';</script>");
	}
	
	
	//Application List
	@RequestMapping(value="applylist3.do")
	public String applylist3(HttpServletRequest request,HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {
			sb.append(" username like '%" + username + "%' ");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dorms.dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" dorms.roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}
		
		
		sb.append(" deletestatus=0  and status!='Cancel_application' order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = applyDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Apply> list = applyDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "applylist3.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "apply/applylist3.do");
		request.setAttribute("url2", "apply/apply");
		request.setAttribute("title", "Dorm Application List");
		
		return "apply/applylist3";

	}
}
