package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

import com.favorite.model.FavoriteDAO;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		HttpSession session = req.getSession();
		
		
		MemberVO member = (MemberVO)session.getAttribute("userid");
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		Integer id = member.getMember_id();
		String nickname = req.getParameter("nickname");
		
		String email = req.getParameter("email");
		String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
		if(email == null || (email.trim()).length() == 0) {
			errorMsgs.put("email", "請勿空白");
		}else if(!email.trim().matches(emailRex)) {
			errorMsgs.put("email", "請輸入正確格式");
		}
		
		String address = req.getParameter("address");
		if(address == null || (address.trim()).length() == 0) {
			errorMsgs.put("address", "請勿空白");
		}
		
		Integer gender = Integer.valueOf(req.getParameter("gender"));
		
		String phone = req.getParameter("phone");
		String phoneRex = "\\d{10}";
		if(phone == null || (phone.trim()).length() == 0) {
			errorMsgs.put("phone", "請勿空白");
		}else if(!phone.trim().matches(phoneRex)) {
			errorMsgs.put("phone", "請輸入10個數字");
		}

		String cp = req.getContextPath();
		if (!errorMsgs.isEmpty()) {
			System.out.println("失敗");
			resp.sendRedirect(cp+"/front-end/member/jsp/member_center.jsp");
			return; // 程式中斷
		}else {
			MemberVO member1 = service.findByPrimaryKey(id);
			member1.setNickname(nickname);
			member1.setAddress(address);
			member1.setEmail(email);
			member1.setGender(gender);
			member1.setPhone(phone);
			
			
			session.setAttribute("userid", member1);
			boolean member2 = service.update(member1);
			
			System.out.println("成功");			
			resp.sendRedirect( cp +"/front-end/member/jsp/member_center.jsp");						
			}
		
		}
		
}


