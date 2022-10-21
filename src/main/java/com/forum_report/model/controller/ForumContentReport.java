package com.forum_report.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum_message.model.Forum_messageJNDIDAO;
import com.forum_message.model.Forum_messageVO;
import com.forum_report.model.Forum_reportJNDI;
import com.forum_report.model.Forum_reportVO;
import com.forum_report.model.service.Forum_reportService;
import com.forum_report.model.service.impl.Forum_reportServiceImpl;
import com.google.gson.Gson;
import com.member.vo.MemberVO;

@WebServlet("/ForumContentReport")
public class ForumContentReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Forum_reportService forum_report = new Forum_reportServiceImpl();	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
        if (vo != null) {// 已經登入過
            resp.sendRedirect("/TGA103G1/fornt-end/forum/forumContentReport.jsp");
        } else { // 未登入轉跳到登入畫面
            resp.sendRedirect("/TGA103G1/front-end/member/jsp/login.jsp");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        Forum_reportJNDI jndi = new Forum_reportJNDI();
        
        MemberVO vo = (MemberVO)req.getSession().getAttribute("userid");
        Integer memberId = vo.getMember_id();
        String forumId = req.getParameter("forumId");
        String reasonString = req.getParameter("reason");

        Forum_reportVO forum_reportVO = new Forum_reportVO();
        forum_reportVO.setForum_id(Integer.parseInt(forumId));
        forum_reportVO.setMember_id(memberId);
        reasonString = reasonString.substring(3,reasonString.lastIndexOf("<"));
        forum_reportVO.setReason(reasonString);
//        jndi.insert(forum_reportVO);
        
        
        PrintWriter outPrintWriter = res.getWriter();
        if(jndi.insert(forum_reportVO)) {
        	outPrintWriter.print("檢舉成功");
        } else {
			outPrintWriter.print(false);
		}
        if(outPrintWriter != null) outPrintWriter.close();
//        try(PrintWriter outPrintWriter = res.getWriter()){
//        	outPrintWriter.print("q");
//        }catch (Exception e) {}
    }
}
