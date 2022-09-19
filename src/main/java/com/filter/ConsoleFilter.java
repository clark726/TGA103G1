package com.filter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;
@WebFilter(urlPatterns = {"/admin/console/*"})
public class ConsoleFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(req.getSession().getAttribute("admin") != null) {
			chain.doFilter(req, res);
		}else {
			res.sendRedirect(req.getContextPath()+"/admin/login.jsp");
			return;
		}
	}
		
}