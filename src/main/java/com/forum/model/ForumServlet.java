package com.forum.model;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.forum.model.*;

@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		ForumJNDI jndi = new ForumJNDI();
		List<ForumVO> list = jndi.getAll();
		if(action != null) {
			switch (action) {
			case "watchOneForum": 
				doPostOne(req, res);
				break;
			default:
				System.out.println("action "+action);
			}
		}else {
			res.sendRedirect("/TGA103G1/fornt-end/forum/forum.jsp");
		}
	}
	
	public void doPostOne(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer page = Integer.parseInt(req.getParameter("page"));
		ForumJNDI jndi = new ForumJNDI();
		ForumVO content = jndi.get(page);
//		System.out.println(content.getContent());
		
		req.setAttribute("forumContentMemberId", jndi.findMemberByForumId(page));
		req.setAttribute("vo", content);
		req.setAttribute("forumMessage", jndi.getAllByForumMessage(page));
		req.getRequestDispatcher("/fornt-end/forum/Content.jsp").forward(req, res);
	}
	
}