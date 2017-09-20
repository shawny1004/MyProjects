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

import com.dao.PreferenceDao;
import com.model.Preference;
import com.model.User;
import com.util.Pager;
@Controller
@RequestMapping(value="preference")
public class PreferenceController extends BaseController{

	@Resource(name="preferenceDao")
	private PreferenceDao preferenceDao;

	


	
	
	//My preference
	@RequestMapping(value="preferencelist.do")
	public String preferencelist(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Preference bean = preferenceDao.selectBean(" where  user.id="+user.getId());
		if(bean==null){
			bean = new Preference();
			bean.setUser(user);
			preferenceDao.insertbean(bean);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		

		
		
		request.setAttribute("bean", bean);

		
		request.setAttribute("url", "preference/preferencelist.do");
		request.setAttribute("url2", "preference/preference");
		request.setAttribute("title", "My preference");
		
		return "preference/preferencelist";

	}


	//Redirect
	@RequestMapping(value="preferenceupdate.do")
	public String preferenceupdate(HttpServletRequest request,HttpServletResponse response) {
	
		
		Preference bean = preferenceDao.selectBean(" where id = " + request.getParameter("id"));
		
		if(bean.getRoomType()!=null){
			String[] roomType = bean.getRoomType().split("B");
			
			
			request.setAttribute("b1", roomType[0]);
			request.setAttribute("b2", roomType[1]);
		}
		
		
		request.setAttribute("url", "preference/preferenceupdate2.do?id="+bean.getId());
		request.setAttribute("title", "Edit Preference");
		
		request.setAttribute("bean", bean);
		
		return "preference/preferenceupdate";

	}
	
	

	//Change preference
	@RequestMapping(value="preferenceupdate2.do")
	public void preferenceupdate2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter writer = this.getPrintWriter(response);
		
		String party = request.getParameter("party");
		String pet = request.getParameter("pet");
		String priceLimit = request.getParameter("priceLimit");
		String roomarea = request.getParameter("roomarea");
		String smoke = request.getParameter("smoke");
		String b1 = request.getParameter("b1");
		String b2 = request.getParameter("b2");
		String roomType = b1+"B"+b2+"B";
		
		Preference bean = preferenceDao.selectBean(" where id = " + request.getParameter("id"));
		
		bean.setParty(party);
		bean.setPet(pet);
		bean.setPriceLimit(Double.parseDouble(priceLimit));
		bean.setRoomarea(roomarea);
		bean.setRoomType(roomType);
		bean.setSmoke(smoke);

		
		preferenceDao.updatebean(bean);

		writer.print("<script language='javascript'>alert('Successful');window.location.href='preferencelist.do';</script>");
	}
	

	
	
	
	//Roommate Look Up
	@RequestMapping(value="preferencelist2.do")
	public String preferencelist2(HttpServletRequest request,HttpServletResponse response) {
		
		String roomType = request.getParameter("roomType");
		String roomarea = request.getParameter("roomarea");
		String smoke = request.getParameter("smoke");
		String party = request.getParameter("party");
		String pet = request.getParameter("pet");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (roomType != null && !"".equals(roomType)) {
			sb.append(" roomType like '%" + roomType + "%' ");
			sb.append(" and ");

			request.setAttribute("roomType", roomType);
		}
		
		if (roomarea != null && !"".equals(roomarea)) {
			sb.append(" roomarea like '%" + roomarea + "%' ");
			sb.append(" and ");

			request.setAttribute("roomarea", roomarea);
		}
		if (smoke != null && !"".equals(smoke)) {
			sb.append(" smoke like '%" + smoke + "%' ");
			sb.append(" and ");

			request.setAttribute("smoke", smoke);
		}
		if (party != null && !"".equals(party)) {
			sb.append(" party like '%" + party + "%' ");
			sb.append(" and ");

			request.setAttribute("party", party);
		}
		if (pet != null && !"".equals(pet)) {
			sb.append(" pet like '%" + pet + "%' ");
			sb.append(" and ");

			request.setAttribute("pet", pet);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" deletestatus=0 and user.id!="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int pagesize = 10;
		int currentpage = 1;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		long total = preferenceDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		
		List<Preference> list = preferenceDao.selectBeanlist((currentpage - 1)* pagesize, pagesize, where);
		
		request.setAttribute("list", list);
		
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize,currentpage, "preference/preferencelist2.do", "There are" + total + "records.");
		
		request.setAttribute("pageinfo", pagerinfo);
		
		request.setAttribute("url", "preference/preferencelist2.do");
		request.setAttribute("url2", "preference/preference");
		request.setAttribute("title", "Roomates Lookup");
		
		return "preference/preferencelist2";

	}
}
