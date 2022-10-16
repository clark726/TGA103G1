package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.PrintServiceAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.service.impl.ForumServiceImpl;

@WebServlet("/AddViewCount")
public class AddViewCount extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("addView".equals(action)) {
			try(PrintWriter out = resp.getWriter()) {
				String forumId = req.getParameter("forumId");
				if(new ForumServiceImpl().addViewCount(Integer.parseInt(forumId))) {
					out.print(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
