package com.common;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;

@WebFilter(urlPatterns = { "/StoreLogin" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR })
public class StoreFilter extends HttpFilter implements Filter {
	private Gson gson = new Gson();
	private StoreService StoreSvc = new StoreServiceImpl();

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();

		if (session.getAttribute("store") == null) {
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					if ("storeRember".equals(name)) {
						String value = cookie.getValue();
						StoreVO storeVO = StoreSvc.findStoreAccount(value);
						session.setAttribute("store", storeVO);
						System.out.println(storeVO.getAccount());
						chain.doFilter(request, response);
					}
				}
			}
		}
		chain.doFilter(request, response);

	}

}
