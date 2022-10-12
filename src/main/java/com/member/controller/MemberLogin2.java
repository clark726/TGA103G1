package com.member.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.lang.model.element.NestingKind;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.LocalDateAdapter;
import com.favorite.model.FavoriteDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;

@WebServlet("/member/login2")
public class MemberLogin2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MemberService service;
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	
	@Override
	public void init() throws ServletException {
			service = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberVO member = _gson.fromJson(request.getReader().readLine(), MemberVO.class);		
		
		System.out.println(member);
		
		member = service.selectByUsername(member.getAccount());

		boolean result = service.login(member.getAccount(),member.getPassword());
		

		response.setContentType("application/json");
		System.out.println(result);
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("logined", true);
			session.setAttribute("userid", member);
			String id = session.getId();
			System.out.println("登入成功");	
			try (PrintWriter pw = response.getWriter()) {
				pw.print(_gson.toJson(member));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("登入失敗");
			request.getRequestDispatcher("/front-end/member/login.html").forward(request, response);
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

