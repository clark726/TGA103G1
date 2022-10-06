package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.JsonUtil;



//@WebFilter(urlPatterns = {"/*"})
public class CookieFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				System.out.println("cookie name: "+name+"  value: "+value);
				if("barjarjo".equals(name)) {
//					System.out.println(JsonUtil.managerToString(value));
				}
			}
		}
		super.doFilter(req, res, chain);
	}
}
