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
import com.store_img.model.Store_imgVO;
import com.store_img.service.Store_imgService;
import com.store_img.service.impl.Store_imgServiceImpl;

@WebServlet("/StoreGetbackInformation")
public class StoreGetbackInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private Store_imgService storeImgSvc = new Store_imgServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Store_imgVO storeimgvo = _gson.fromJson(request.getReader().readLine(), Store_imgVO.class);
		List<Store_imgVO> list = storeImgSvc.getbackInformation(storeimgvo.getAccount());
		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(list));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
