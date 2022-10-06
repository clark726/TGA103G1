package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;

@WebServlet("/StoreSumit")
public class StoreSumit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	StoreService service = new StoreServiceImpl() ;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StoreVO store = _gson.fromJson(request.getReader().readLine(), StoreVO.class);
		service.storeSumit(store);
		System.out.println(store.getLat());
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(store));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
