package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.DormsDao;
import com.model.Dorms;
import com.util.Pager;
import com.util.Util;
@Controller
@RequestMapping(value="dorm")
public class DormController extends BaseController{

	@Resource(name="dormsDao")
	private DormsDao dormsDao;


	
	//Dorm List
	@RequestMapping(value="dormslist.do")
	public String dormslist(HttpServletRequest request,HttpServletResponse response) {
		
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}

		sb.append(" deletestatus=0 order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = dormsDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Dorms> list = dormsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "dormslist.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "dorm/dormslist.do");
		request.setAttribute("url2", "dorm/dorms");
		request.setAttribute("title", "Dorm Management");
		
		return "dorms/dormslist";

	}

	

	//Redirect
	@RequestMapping(value="dormsadd.do")
	public String dormsadd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("url", "dorm/dormsadd2.do");
		request.setAttribute("title", "Add Dorm");
		return "dorms/dormsadd";
	}
	
	private File uploadfile;

	
	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	//add dorm
	@RequestMapping(value="dormsadd2.do")
	public void dormsadd2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		
		String area = request.getParameter("area");
		String dormNum = request.getParameter("dormNum");
		String number_of_room = request.getParameter("number_of_room");
		String price = request.getParameter("price");
		String roomNum = request.getParameter("roomNum");
		
		String b1 = request.getParameter("b1");
		String b2 = request.getParameter("b2");
		
		String roomType = b1+"B"+b2+"B";
		
		
		Dorms bean = new Dorms();
		
		bean.setArea(area);
		bean.setDormNum(dormNum);
		if(uploadfile!=null){
			String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time+".jpg";
			File file = new File(savapath+imgpath);
			Util.copyFile(uploadfile, file);
			bean.setImage(imgpath);
		}
		
		bean.setNumber_of_room(Integer.parseInt(number_of_room));
		bean.setPrice(price);
		bean.setRoomNum(roomNum);
		bean.setRoomType(roomType);
		
		
		dormsDao.insertbean(bean);
		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist.do';</script>");

		
		
		
		
	}

	//redirect
	@RequestMapping(value="dormsupdate.do")
	public String dormsupdate(HttpServletRequest request,HttpServletResponse response) {
	
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		
		
		String[] roomType = bean.getRoomType().split("B");
		
		request.setAttribute("b1", roomType[0]);
		request.setAttribute("b2", roomType[1]);
		
		request.setAttribute("url", "dorm/dormsupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Dorm");
		
		request.setAttribute("bean", bean);
		
		return "dorms/dormsupdate";

	}
	
	

	//Edit Dorm
	@RequestMapping(value="dormsupdate2.do")
	public void dormsupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		String area = request.getParameter("area");
		String dormNum = request.getParameter("dormNum");
		String number_of_room = request.getParameter("number_of_room");
		String price = request.getParameter("price");
		String roomNum = request.getParameter("roomNum");
		String b1 = request.getParameter("b1");
		String b2 = request.getParameter("b2");
		
		String roomType = b1+"B"+b2+"B";
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setArea(area);
		bean.setDormNum(dormNum);
		
		
		bean.setNumber_of_room(Integer.parseInt(number_of_room));
		bean.setPrice(price);
		bean.setRoomNum(roomNum);
		bean.setRoomType(roomType);

		
		dormsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist.do';</script>");
	}
	

	// Delete dorm
	@RequestMapping(value="dormsdelete.do")
	public void dormsdelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setDeletestatus(1);
		dormsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist.do';</script>");

	}

	
	//Redirect
	@RequestMapping(value="dormsupdate3.do")
	public String dormsupdate3(HttpServletRequest request,HttpServletResponse response ) {

		request.setAttribute("title", "View Dorm");
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		return "dorms/dormsupdate3";

	}
	
	
	
	//redirect
	@RequestMapping(value="dormsupdate5.do")
	public String dormsupdate5(HttpServletRequest request,HttpServletResponse response) {
	
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		
		request.setAttribute("url", "dorm/dormsupdate6.do?id="+bean.getId());
		request.setAttribute("title", "ReUpload picture");
		
		request.setAttribute("bean", bean);
		
		return "dorms/dormsupdate5";

	}
	

	//edit dorm
	@RequestMapping(value="dormsupdate6.do")
	public void dormsupdate6(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
	
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		
		if(uploadfile!=null){
			String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time+".jpg";
			File file = new File(savapath+imgpath);
			Util.copyFile(uploadfile, file);
			bean.setImage(imgpath);
		}

		
		dormsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist.do';</script>");
	}
	
	
	
	
	//Dorm Look Up
	@RequestMapping(value="dormslist2.do")
	public String dormslist2(HttpServletRequest request,HttpServletResponse response) {
		
		String roomType = request.getParameter("roomType");
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (roomType != null && !"".equals(roomType)) {
			sb.append(" roomType like '%" + roomType + "%' ");
			sb.append(" and ");

			request.setAttribute("roomType", roomType);
		}
		
		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}

		sb.append(" deletestatus=0 and (number_of_room-number_of_use)>0  order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = dormsDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Dorms> list = dormsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "dorm/dormslist2.do", "There are" + total + "List");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "dorm/dormslist2.do");
		request.setAttribute("url2", "dorm/dorms");
		request.setAttribute("title", "Dorm Look Up");
		
		return "dorms/dormslist2";

	}
	

	
	//Ad list
	@RequestMapping(value="dormslist3.do")
	public String dormslist3(HttpServletRequest request,HttpServletResponse response) {
		
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}

		sb.append(" deletestatus=0 order by advertisement desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = dormsDao.selectBeanCount(where.replaceAll("order by advertisement desc", ""));
		
		List<Dorms> list = dormsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "dorm/dormslist3.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "dorm/dormslist3.do");
		request.setAttribute("url2", "dorm/dorms");
		request.setAttribute("title", "Advertisement Management");
		
		return "dorms/dormslist3";

	}
	
	
	//Sed Ad
	@RequestMapping(value="dormsdelete2.do")
	public void dormsdelete2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		List<Dorms> list = dormsDao.selectBeanlist(0, 9999, " where deletestatus=0  ");
		for(Dorms d:list){
			d.setAdvertisement(0);
			dormsDao.updatebean(d);
		}
		
		
		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setAdvertisement(1);
		dormsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist3.do';</script>");

	}
	
	
	//cancel advertisement
	@RequestMapping(value="dormsdelete3.do")
	public void dormsdelete3(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		

		Dorms bean = dormsDao.selectBean(" where id = " + request.getParameter("id"));
		bean.setAdvertisement(0);
		dormsDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='dormslist3.do';</script>");

	}
	
	
	//Residents
	@RequestMapping(value="dormslist4.do")
	public String dormslist4(HttpServletRequest request,HttpServletResponse response ) {
		
		String dormNum = request.getParameter("dormNum");
		String roomNum = request.getParameter("roomNum");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (dormNum != null && !"".equals(dormNum)) {
			sb.append(" dormNum like '%" + dormNum + "%' ");
			sb.append(" and ");

			request.setAttribute("dormNum", dormNum);
		}
		
		if (roomNum != null && !"".equals(roomNum)) {
			sb.append(" roomNum like '%" + roomNum + "%' ");
			sb.append(" and ");

			request.setAttribute("roomNum", roomNum);
		}

		sb.append(" deletestatus=0 order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = dormsDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Dorms> list = dormsDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "dorm/dormslist4.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "dorm/dormslist4.do");
		request.setAttribute("url2", "dorm/dorms");
		request.setAttribute("title", "Resident Students");
		
		return "dorms/dormslist4";

	}
}
