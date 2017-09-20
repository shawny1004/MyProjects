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

import com.dao.MessageDao;
import com.dao.UserDao;
import com.model.Message;
import com.model.User;
import com.util.Pager;
import com.util.Util;

@Controller
@RequestMapping(value="message")
public class MessageController extends BaseController{



	@Resource(name="messageDao")
	private MessageDao messageDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	//Sent Box
	@RequestMapping(value="messagelist.do")
	public String messagelist(HttpServletRequest request,HttpServletResponse response ) {
		
		String messageTitle = request.getParameter("messageTitle");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (messageTitle != null && !"".equals(messageTitle)) {
			sb.append(" messageTitle like '%" + messageTitle + "%' ");
			sb.append(" and ");

			request.setAttribute("messageTitle", messageTitle);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" deletestatus1=0  and fromuser.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = messageDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Message> list = messageDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "message/messagelist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "message/messagelist.do");
		request.setAttribute("url2", "message/message");
		request.setAttribute("title", "Sent box List");
		
		return "message/messagelist";

	}

	

	//redirect
	@RequestMapping(value="messageadd.do")
	public String messageadd(HttpServletRequest request,HttpServletResponse response) {
		String touserid = request.getParameter("touserid");
		request.setAttribute("url", "message/messageadd2.do?touserid="+touserid);
		request.setAttribute("title", "Send Message");
		return "message/messageadd";
	}
	

	//Send Msg
	@RequestMapping(value="messageadd2.do")
	public void messageadd2(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String messageContent = request.getParameter("messageContent");
		String messageTitle = request.getParameter("messageTitle");
		String touserid = request.getParameter("touserid");
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Message bean = new Message();
		bean.setCreatetime(Util.getTime3());
		bean.setFromuser(user);
		bean.setMessageContent(messageContent);
		bean.setMessageTitle(messageTitle);
		bean.setStatus("Unread");
		bean.setTouser(userDao.selectBean(" where id= "+touserid));
		
		messageDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist.do';</script>");
		
		
		
	}

	

	// Delete Message
	@RequestMapping(value="messagedelete.do")
	public void messagedelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Message bean = messageDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus1(1);
		messageDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist.do';</script>");

	}
	
	
	//Redirect
	@RequestMapping(value="messageupdate3.do")
	public String messageupdate3(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Message");
		
		Message bean = messageDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "message/messageupdate3";

	}
	
	
	
	
	//Inbox
	@RequestMapping(value="messagelist2.do")
	public String messagelist2(HttpServletRequest request,HttpServletResponse response ) {
		
		String messageTitle = request.getParameter("messageTitle");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (messageTitle != null && !"".equals(messageTitle)) {
			sb.append(" messageTitle like '%" + messageTitle + "%' ");
			sb.append(" and ");

			request.setAttribute("messageTitle", messageTitle);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" deletestatus2=0  and touser.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = messageDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Message> list = messageDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "message/messagelist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "message/messagelist2.do");
		request.setAttribute("url2", "message/message");
		request.setAttribute("title", "Inbox");
		
		return "message/messagelist2";

	}
	
	
	// Delete
	@RequestMapping(value="messagedelete2.do")
	public void messagedelete2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Message bean = messageDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus2(1);
		messageDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist2.do';</script>");

	}
	
	
	//Redirect
	@RequestMapping(value="messageupdate5.do")
	public String messageupdate5(HttpServletRequest request,HttpServletResponse response) {

		request.setAttribute("title", "View Message");
		
		Message bean = messageDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setStatus("Readed");
		
		messageDao.updatebean(bean);
		
		request.setAttribute("bean", bean);
		
		return "message/messageupdate5";

	}
	
	
	//Redirect
	@RequestMapping(value="messageadd3.do")
	public String messageadd3(HttpServletRequest request,HttpServletResponse response ) {
		String messageid = request.getParameter("messageid");
		request.setAttribute("url", "message/messageadd4.do?messageid="+messageid);
		request.setAttribute("title", "Reply");
		return "message/messageadd3";
	}
	

	//Reply
	@RequestMapping(value="messageadd4.do")
	public void messageadd4(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		Message oldmessage = messageDao.selectBean(" where id = " + request.getParameter("messageid"));
		
		String messageContent = request.getParameter("messageContent");
		String messageTitle = request.getParameter("messageTitle");
		
		
		Message bean = new Message();
		bean.setCreatetime(Util.getTime3());
		bean.setFromuser(oldmessage.getTouser());
		bean.setMessageContent(messageContent);
		bean.setMessageTitle(messageTitle);
		bean.setStatus("Unread");
		bean.setTouser(oldmessage.getFromuser());
		
		messageDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist.do';</script>");
		
		
		
	}
	
	
	//redirect
	@RequestMapping(value="messageadd5.do")
	public String messageadd5(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "message/messageadd6.do");
		request.setAttribute("title", "Send Message to admin ");
		return "message/messageadd5";
	}
	

	//send message to admin
	@RequestMapping(value="messageadd6.do")
	public void messageadd6(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String messageContent = request.getParameter("messageContent");
		String messageTitle = request.getParameter("messageTitle");
		User touser = userDao.selectBean(" where username='admin' ");
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Message bean = new Message();
		bean.setCreatetime(Util.getTime3());
		bean.setFromuser(user);
		bean.setMessageContent(messageContent);
		bean.setMessageTitle(messageTitle);
		bean.setStatus("Unread");
		bean.setTouser(touser);
		
		messageDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist.do';</script>");
		
		
	}
	
	@RequestMapping(value="messageadd7.do")
	public String messageadd7(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "message/messageadd8.do");
		request.setAttribute("title", "Send Message");
		
		request.setAttribute("userlist", userDao.selectBeanlist(0, 9999, " where role!=1 and deletestatus=0 "));
		return "message/messageadd7";
	}
	

	//Send Message
	@RequestMapping(value="messageadd8.do")
	public void messageadd8(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String messageContent = request.getParameter("messageContent");
		String messageTitle = request.getParameter("messageTitle");
		User touser = userDao.selectBean(" where id= "+request.getParameter("touserid"));
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Message bean = new Message();
		bean.setCreatetime(Util.getTime3());
		bean.setFromuser(user);
		bean.setMessageContent(messageContent);
		bean.setMessageTitle(messageTitle);
		bean.setStatus("Unread");
		bean.setTouser(touser);
		
		messageDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='messagelist.do';</script>");
		
		
		
	}
}
