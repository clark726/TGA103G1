package com.forum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.ForumJNDI;
import com.forum.model.ForumVO;


@WebServlet("/SearchForum")
public class SearchForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String str = req.getParameter("serach");
		List<ForumVO> vos = new ForumJNDI().getserach(str);
		req.setAttribute("list", vos);
		if(vos.isEmpty()) {
			req.setAttribute("error", "查無此文章");
		}
		req.getRequestDispatcher("/fornt-end/forum/forum.jsp").forward(req, res);
		return;
	}
}
