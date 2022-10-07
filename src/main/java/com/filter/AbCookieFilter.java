package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forum_report.model.service.impl.Forum_reportServiceImpl;
import com.manager.model.ManagerVO;
import com.manager.model.service.impl.ManagerServiceImpl;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;
import com.message_report.model.service.impl.Message_reportServiceImpl;
import com.product.model.ProductVO;
import com.product.service.impl.ProductServiceImpl;
import com.util.BeansFactory;
import com.util.JsonUtil;



@WebFilter(urlPatterns = {"/*"},filterName = "AAA",
dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR})
public class AbCookieFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			HttpSession session = req.getSession();
			if(session.getAttribute("admin") == null) {
				for(Cookie cookie:cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("barjarjo".equals(name)) {
						session.setAttribute("admin", JsonUtil.base64ToManager(value));
						int datas = 5;
						List<MemberVO> memberList = BeansFactory.getInstance("MemberServiceImpl", MemberServiceImpl.class).getAll();
						session.setAttribute("members", memberList);
						session.setAttribute("memberPages",
								memberList.size() % datas == 0 ? memberList.size() / datas : memberList.size() / datas + 1);
						session.setAttribute("messageReportList", BeansFactory.getInstance("Message_reportServiceImpl",
								Message_reportServiceImpl.class).getAll());
						session.setAttribute("datas", datas);
						List<ManagerVO> list = BeansFactory.getInstance("ManagerServiceImpl", ManagerServiceImpl.class).getAll();
						session.setAttribute("admins", list);
						ArrayList<ProductVO> products = (ArrayList<ProductVO>) BeansFactory.getInstance("ProductServiceImpl", ProductServiceImpl.class).getAll();
						int listing = 0;
						for (int x = 0; x < products.size(); x++) {
							if (products.get(x).getStatus() == 1) {
								listing++;
							}
						}
						session.setAttribute("products", products);
						session.setAttribute("listing", listing);
						session.setAttribute("productStatus", 3);
						session.setAttribute("forumReport", BeansFactory.getInstance("Forum_reportServiceImpl", Forum_reportServiceImpl.class).getAll());
					}
				}
			}
		}
		super.doFilter(req, res, chain);
	}
}
