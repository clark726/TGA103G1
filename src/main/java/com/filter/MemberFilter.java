package com.filter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;
@WebFilter(urlPatterns = {"/front-end/member/jsp/*"},
dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR})
public class MemberFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(req.getSession().getAttribute("userid") != null) {
			chain.doFilter(req, res);
		}else {
			res.sendRedirect(req.getContextPath()+"/front-end/member/login.jsp");
			return;
		}
	}
		
}