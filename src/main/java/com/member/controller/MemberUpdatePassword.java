package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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

@WebServlet("/member/MemberUpdatePassword")
public class MemberUpdatePassword extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
			service = new MemberServiceImpl();
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("userid");
		
		Integer id = member.getMember_id();
		String oldpassword = member.getPassword();
		
		
		MemberVO member1 = service.findByPrimaryKey(id);
		String password = req.getParameter("oldpassword");
		
		
		if(!password.equals(oldpassword)) {
			errorMsgs.put("password", "舊密碼不正確");
		}
		
		String newpassword = req.getParameter("newpassword");
		System.out.println(newpassword);
		String psRex = "^[a-zA-z0-9]{4,20}$";
		if(!newpassword.trim().matches(psRex)) {
			errorMsgs.put("newpassword", "請輸入4-20個英文或數字");
		}
		
		String dbpassword = req.getParameter("dbpassword");
		System.out.println(dbpassword);
		if(newpassword == null || newpassword.isEmpty()) {
			errorMsgs.put("newpassword", "密碼不能空白");
		}
		if(dbpassword == null || dbpassword.isEmpty()){
			errorMsgs.put("dbpassword","請勿空白");
		}
		if(!dbpassword.equals(newpassword)){
			errorMsgs.put("dbpassword","兩次密碼不一致");
		}
		member1.setPassword(newpassword);						
//		for(Map.Entry<String, String> entry:errorMsgs.entrySet()) {
//			System.out.println(entry.getKey()+"  "+entry.getValue());
//		}

		String cp = req.getContextPath();
		if (!errorMsgs.isEmpty()) {
			System.out.println("失敗");
			resp.sendRedirect(cp+"/front-end/member/jsp/member_centerChangePsw.jsp");
			return; // 程式中斷
		}else {
			boolean member2 = service.updatePassword(member1);
			System.out.println(member2);
			System.out.println("成功");
			resp.sendRedirect(cp+"/front-end/member/jsp/member_center.jsp");
		}

	}
}

