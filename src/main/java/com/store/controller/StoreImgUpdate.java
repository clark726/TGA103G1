package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.model.StoreVO;
import com.store_img.model.Store_imgVO;
import com.store_img.service.Store_imgService;
import com.store_img.service.impl.Store_imgServiceImpl;

@WebServlet("/StoreImgUpdate")
public class StoreImgUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private Store_imgService storeImgSvc = new Store_imgServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
			Store_imgVO storeimg;
			storeimg = _gson.fromJson(request.getReader().readLine(), Store_imgVO.class);
			// 取出存在session的storeId放入前端傳來的store
			HttpSession session = request.getSession();
			StoreVO storevoId = (StoreVO) session.getAttribute("storeId");
			Integer storeId = storevoId.getStore_id();
			storeimg.setStoreId(storeId);

			storeImgSvc.insert(storeimg);
		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(storeimg));

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
