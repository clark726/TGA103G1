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
import com.google.gson.Gson;
import com.member.vo.MemberVO;
import com.store.model.StoreVO;

@WebServlet("/PostForumContent")
public class PostForumContent extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action =  req.getParameter("action");
        MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
        if (vo != null) {// 已經登入過
            resp.sendRedirect("/TGA103G1/fornt-end/forumContentInput/forumContentInput.jsp");
        } else { // 未登入轉跳到登入畫面
            resp.sendRedirect("/TGA103G1/front-end/member/jsp/login.jsp");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
//        String action = req.getParameter("action");
        ForumJNDI jndi = new ForumJNDI();
        
        String title = req.getParameter("forumContentTitle");
        String context = req.getParameter("editorDemo");
        context = context.substring(3,context.lastIndexOf("<"));
        MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
        Integer id = vo.getMember_id();
        ForumVO forumVO = new ForumVO();
        forumVO.setMember_id(id);
        forumVO.setContent(context);
        forumVO.setTitle(title);
        jndi.add(forumVO);
        res.sendRedirect("/TGA103G1/fornt-end/forum/forum.jsp");       
    }
}
