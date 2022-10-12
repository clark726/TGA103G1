package com.member.controller;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favorite.model.FavoriteDAO;
import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/member/login")
public class MemberLogin extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
			service = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");

		if(account == null || (account.trim()).length() == 0) {
			errorMsgs.put("account", "請勿空白");
		}
		if(password == null || (password.trim()).length() == 0) {
			errorMsgs.put("password", "請勿空白");
		}
		
		
		MemberVO member = service.selectByUsername(account);

		boolean result = service.login(account, password);
	
		String cp = req.getContextPath();
		if(result) {
//			Cookie u = new Cookie("account", account);
//			u.setMaxAge(60*60*24);
//			u.setPath("/");
//			resp.addCookie(u);
			
			session.setAttribute("logined", true);
			session.setAttribute("userid", member);
			String id = session.getId();
			System.out.println("登入成功");
			if(req.getSession().getAttribute("url")!=null) {
				resp.sendRedirect((String)req.getSession().getAttribute("url"));
				session.removeAttribute("url");
				return;
			}else {
				resp.sendRedirect(cp +"/front-end/member/jsp/member_center.jsp");				
				return;
			}
		}else {
			
			req.setAttribute("result", "帳號或密碼錯誤");
			req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, resp);
		}
	}

}

