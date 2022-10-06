package com.order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.vo.MemberVO;
import com.order_detail.model.Order_detailVO;
import com.order_detail.service.Order_detailService;
import com.order_detail.service.impl.Order_detailServiceImpl;

@WebServlet("/memberOrder")
public class MemberOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	private Order_detailService service;
	
	@Override
	public void init() throws ServletException {
			service = new Order_detailServiceImpl();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		Integer memberId = ((MemberVO) session.getAttribute("userid")).getMember_id();
		
		
		
		Order_detailVO od = _gson.fromJson(request.getReader().readLine(), Order_detailVO.class);
	
		
		List<Order_detailVO> list = service.getAllBymember(memberId);
		
		
		try (PrintWriter pw = response.getWriter()) {
			pw.print(_gson.toJson(list));
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
