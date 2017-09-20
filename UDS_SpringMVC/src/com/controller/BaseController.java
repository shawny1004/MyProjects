package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class BaseController {
	
	public PrintWriter getPrintWriter(HttpServletResponse response) {

		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer;
	}
}
