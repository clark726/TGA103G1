package com.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "charset", value = "UTF-8")})
public class EncoderFilter extends HttpFilter{
	public static final String DEFAULT_ENCODING = "UTF-8";
    private String charset;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.charset = config.getInitParameter("charset");
        if(this.charset==null || "".equals(this.charset)){
            this.charset = DEFAULT_ENCODING;
        }
    }
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        chain.doFilter(req,resp);
    }
}