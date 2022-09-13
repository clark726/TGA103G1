package com.member.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;
@WebFilter(urlPatterns = {"/admin/*"})
public class LoginFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(req.getSession().getAttribute("id") != null) {
			chain.doFilter(req, res);
		}else {
			res.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		}
	}
		
}
