package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;
import com.util.JsonUtil;

@WebServlet("/StoreLogin")
public class StoreLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private StoreService StoreSvc = new StoreServiceImpl();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StoreVO store = _gson.fromJson(request.getReader().readLine(), StoreVO.class);
		HttpSession session = request.getSession();
		StoreSvc.login(store);
		if(session.getAttribute("store") != null) {
			StoreVO storeVO = (StoreVO) session.getAttribute("store");
			storeVO.setSuccessful(true);
			PrintWriter pw = response.getWriter();
			pw.print(new GsonBuilder().create().toJson(storeVO));
			return;
		}
		if (store.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			
			Cookie cookie = new Cookie("storeRember", store.getAccount() );
			cookie.setMaxAge(604800);
			response.addCookie(cookie);
			
			session.setAttribute("loggedin", true);
			session.setAttribute("store", store);
		}
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(store));
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
