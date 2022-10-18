package com.message_report.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum_report.model.Forum_reportJNDI;
import com.forum_report.model.Forum_reportVO;
import com.forum_report.model.service.Forum_reportService;
import com.forum_report.model.service.impl.Forum_reportServiceImpl;
import com.member.vo.MemberVO;
import com.message_report.model.Message_reportJNDIDAO;
import com.message_report.model.service.Message_reportService;
import com.message_report.model.service.impl.Message_reportServiceImpl;

@WebServlet("/MessageReport")
public class MessageReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	    private Message_reportService message_report = new Message_reportServiceImpl();	
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
	        if (vo != null) {// 已經登入過
	        	System.out.println(123);
	        } else { // 未登入轉跳到登入畫面
	            resp.sendRedirect("/TGA103G1/front-end/member/jsp/login.jsp");
	        }
	    }
	    public void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        req.setCharacterEncoding("UTF-8");
	        res.setCharacterEncoding("UTF-8");
	        Message_reportJNDIDAO jndi = new Message_reportJNDIDAO();
	        
	        MemberVO vo = (MemberVO)req.getSession().getAttribute("userid");
	        Integer memberId = vo.getMember_id();
	        String forumId = req.getParameter("forumId");
	        String reasonString = req.getParameter("reason");

	        Forum_reportVO forum_reportVO = new Forum_reportVO();
	        forum_reportVO.setForum_id(Integer.parseInt(forumId));
	        forum_reportVO.setMember_id(memberId);
	        forum_reportVO.setReason(reasonString);
	    }
	}
