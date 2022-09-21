package com.member.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/member/register")
public class MemberRegister extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new MemberServiceImpl();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String checkpassword = req.getParameter("dbpassword");
		String birthday  = req.getParameter("birthday");
		LocalDate birthday2 = LocalDate.parse(birthday);
		String city = req.getParameter("city");
		String dist = req.getParameter("dist");
		String addres = req.getParameter("address");
		String address = city + dist + addres;
		String gender = req.getParameter("gender");
		Integer gender1 = Integer.parseInt(gender);
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");
		String phone = req.getParameter("phone");
		
		MemberVO member = new MemberVO();
		member.setAccount(account);
		member.setPassword(password);
		member.setBirthday(birthday2);
		member.setAddress(address);
		member.setGender(gender1);
		member.setEmail(email);
		member.setNickname(nickname);
		member.setPhone(phone);
		
		
		boolean result = service.register(member);
		req.setAttribute("result", result ? "成功" : "失敗");
		req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		
	}
}

