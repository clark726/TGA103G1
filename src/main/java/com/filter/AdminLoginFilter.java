package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/admin/login.jsp"})
public class AdminLoginFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(req.getSession().getAttribute("admin") == null) {
			chain.doFilter(req, res);
		}else {
			res.sendRedirect(req.getContextPath()+"/admin/console/members.jsp");
			return;
		}
	}
}
