package com.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StoreLogout")
public class StoreLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				if("storeRember".equals(name)) {
					Cookie cookie2 = new Cookie("storeRember", null);
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
				}
			}
		}
		
		String url = request.getContextPath() + "/main.html";
		response.sendRedirect(url);
	}

}
