package com.forum_message.controller;
    import java.io.IOException;
    import java.util.List;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import com.forum.model.ForumJNDI;
    import com.forum.model.ForumVO;
import com.forum_message.model.Forum_messageJNDIDAO;
import com.forum_message.model.Forum_messageVO;
import com.google.gson.Gson;
    import com.member.vo.MemberVO;
    import com.store.model.StoreVO;

    @WebServlet("/PostForumContentMessage")
    public class PostForumContentMessage extends HttpServlet {
        private static final long serialVersionUID = 1L;


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
            if (vo != null) {// 已經登入過
                resp.sendRedirect("/TGA103G1/fornt-end/forum/Content.jsp");
            } else { // 未登入轉跳到登入畫面
                resp.sendRedirect("/TGA103G1/front-end/member/jsp/login.jsp");
            }
        }
        public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");
            Forum_messageJNDIDAO jndi = new Forum_messageJNDIDAO();
            
            
            String memberId = req.getParameter("memberId");
            String forumId = req.getParameter("forumId");
            String content = req.getParameter("content");
//            MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
//            Integer id = vo.getMember_id();
//            ForumVO vo2 = (ForumVO) req.getSession().getAttribute("forumid");
//            Integer id2 = vo2.getForum_id();
//            String content = req.getParameter("editorContentMessage");
//            content = content.substring(3,content.lastIndexOf("<"));
            Forum_messageVO forum_messageVO = new Forum_messageVO();
            forum_messageVO.setMember_id(Integer.parseInt(memberId));
            forum_messageVO.setForum_id(Integer.parseInt(forumId));
            forum_messageVO.setContent(content);
            jndi.insert(forum_messageVO);
            req.setAttribute("postMessage", content);
            res.getWriter().print(123);
//            res.sendRedirect("/TGA103G1/fornt-end/forum/Content.jsp");
//            System.out.println(memberId);
//            System.out.println(forumId);
//            System.out.println(content);
            
        }
    }
    
