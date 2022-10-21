package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.service.impl.ForumServiceImpl;

/**
 * Servlet implementation class AddViewMessageCount
 */
@WebServlet("/AddViewMessageCount")
public class AddViewMessageCount extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("addView".equals(action)) {
			try(PrintWriter out = resp.getWriter()) {
				String messageId = req.getParameter("messageId");
				if(new ForumServiceImpl().addViewCount(Integer.parseInt(messageId))) {
					out.print(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
