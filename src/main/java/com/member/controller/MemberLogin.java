package com.member.controller;
import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/member/login")
public class MemberLogin extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new MemberServiceImpl();
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();

		MemberVO member = new MemberVO();
		member.setAccount(account);
		member.setPassword(password);
		
		MemberVO result = service.login(member);
	
		System.out.println(result);
		String userid = "";
		
		if(result.getAccount().equals(account) && result.getPassword().equals(password)) {
//			Cookie u = new Cookie("account", account);
//			u.setMaxAge(60*60*24);
//			u.setPath("/");
//			resp.addCookie(u);
			userid = result.getAccount();
			req.setAttribute("memberVO", result);
			session.setAttribute("userid", userid);
			
			req.getRequestDispatcher("/member/member_center.jsp").forward(req, resp);
		}else {
			
			req.getRequestDispatcher("/member/register.jsp").forward(req, resp);
		}
	}
}

