package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/ShowStorePage")
public class ShowStorePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private StoreService storeSvc = new StoreServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Integer store_id = Integer.parseInt(request.getParameter("storeId")); 
		List<StoreVO> list =  storeSvc.findStorepageByStoreId(store_id);
		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
