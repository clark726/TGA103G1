package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.order.model.OrderVO;
import com.order.service.OrderService;
import com.order.service.impl.OrderServiceImpl;

@WebServlet("/SelectOrderByAccount")
public class SelectOrderByAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private OrderService orderSvc = new OrderServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrderVO orderVO = _gson.fromJson(request.getReader().readLine(), OrderVO.class);

		String account = orderVO.getAccount();
		List<OrderVO> list = orderSvc.getAllByStoreAccount(account);

		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(list));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
