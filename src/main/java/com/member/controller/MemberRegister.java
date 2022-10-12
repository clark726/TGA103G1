package com.member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/register")
public class MemberRegister extends HttpServlet{

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
		
		LocalDate birthday2 = null;
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String checkpassword = req.getParameter("dbpassword");
		String birthday  = req.getParameter("birthday");
		if(birthday == "") {
			errorMsgs.put("birthday", "請輸入生日");
		}else {
			birthday2 = LocalDate.parse(birthday);			
		}
		String city = req.getParameter("city");
		String dist = req.getParameter("dist");
		String addres = req.getParameter("address");
		String address = city + dist + addres;
		String gender = req.getParameter("gender");
		Integer gender1 = Integer.parseInt(gender);
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");
		String phone = req.getParameter("phone");
		
		
		if(account == null || (account.trim()).length() == 0) {
			errorMsgs.put("account", "請勿空白");
		}
		String psRex = "^[a-zA-z0-9]{4,20}$"; 
		if(password == null || (password.trim()).length() == 0) {
			errorMsgs.put("password", "請勿空白");
		}else if(!password.trim().matches(psRex)) {
			errorMsgs.put("password", "請輸入4-20個英文或數字");
		}
		
		if(checkpassword == null || (checkpassword.trim()).length() == 0) {
			errorMsgs.put("checkpassword", "請勿空白");
		}
		if(!checkpassword.equals(password)) {
			errorMsgs.put("checkpassword","兩次密碼不相同");
		}

		String phoneRex = "^\\d{10}$";
		if(phone == null || (phone.trim()).length() == 0) {
			errorMsgs.put("phone", "請勿空白");
		}else if(!phone.trim().matches(phoneRex)) {
			errorMsgs.put("phone", "請輸入10個數字");
		}
		
		String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
		if(email == null || (email.trim()).length() == 0) {
			errorMsgs.put("email", "請勿空白");
		}else if(!email.trim().matches(emailRex)) {
			errorMsgs.put("email", "請輸入正確格式");
		}
		
		if(address == null || (address.trim()).length() == 0) {
			errorMsgs.put("address", "請勿空白");
		}
		
		
		MemberVO member = new MemberVO();
		member.setAccount(account);
		member.setPassword(password);
		member.setBirthday(birthday2);
		member.setAddress(address);
		member.setGender(gender1);
		member.setEmail(email);
		member.setNickname(nickname);
		member.setPhone(phone);
		
		
//		List<MemberVO> list =  service.getAll();
//		for(MemberVO e : list) {
//			System.out.println(e.getAccount());
//			if(account.equals(e.getAccount())) {
//				errorMsgs.put("account", "重複帳號");
//			}else {
//			}
//	}
		boolean result = service.register(member);
			
		if (!errorMsgs.isEmpty()) {
			System.err.println("註冊失敗");
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/register.jsp");
			failureView.forward(req, resp);
			return; // 程式中斷
		}
		System.err.println("註冊成功");
		RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/login.jsp");
		successView.forward(req, resp);
	}
}

