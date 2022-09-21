package com.filter;

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

@WebServlet("/member/MemberUpdate")
public class MemberUpdate extends HttpServlet{

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
		
		Integer id = Integer.valueOf(req.getParameter("member_id"));
		String account = req.getParameter("account");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		Integer gender = Integer.valueOf(req.getParameter("gender"));
		String phone = req.getParameter("phone");

		HttpSession session = req.getSession();
		MemberVO member = new MemberVO();
		member.setMember_id(id);
		member.setAccount(account);
		member.setNickname(nickname);
		member.setEmail(email);
		member.setAddress(address);
		member.setPhone(phone);
		member.setGender(gender);
		
		
		
		member = service.update(member);
	
		
		
		if(member != null) {
			
			req.setAttribute("member_id", id);
			req.setAttribute("account", account);
			req.setAttribute("nickname", nickname);
			req.setAttribute("email", email);
			req.setAttribute("address", address);
			req.setAttribute("phone", phone);
			req.setAttribute("gender", gender);
			req.setAttribute("memberVO", member);
			session.getAttribute("userid");
			req.getRequestDispatcher("/member/member_center.jsp").forward(req, resp);
		}else {
			
			req.getRequestDispatcher("/member/register.jsp").forward(req, resp);
		}
	}
}

