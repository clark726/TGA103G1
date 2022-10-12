package com.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.order.model.OrderVO;
import com.order.service.OrderService;
import com.order.service.impl.OrderServiceImpl;

@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private OrderService orderSvc = new OrderServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		OrderVO orderVO = _gson.fromJson(request.getReader().readLine(), OrderVO.class);
		orderSvc.insert(orderVO);
		
	}

}
