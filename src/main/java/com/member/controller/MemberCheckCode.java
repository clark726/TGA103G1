package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/memberCheckCode")
public class MemberCheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	
	
	@Override
	public void init() throws ServletException {
			service = new MemberServiceImpl();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		final HttpSession session = request.getSession();
		
		
		final String verification = ((MemberVO) session.getAttribute("forget")).getVerifyAccount();
		MemberVO member = _gson.fromJson(request.getReader().readLine(), MemberVO.class);
		
        member.setVerifyAccount(verification);
        
		member = service.checkCode(member);
		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(_gson.toJson(member));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
	}

	private void setHeaders(HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}
}
