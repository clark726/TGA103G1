package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.product_img.model.Product_imgService;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;

@WebServlet("/ShowStorePage")
public class ShowStorePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private StoreService storeSvc = new StoreServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if ("getImg".equals(action)) {
			String prdStr = request.getParameter("product_id");
			if (prdStr != null) {
				int product_id = Integer.parseInt(prdStr);
				Product_imgService imgService = new Product_imgService();
				byte[] img = imgService.findByProductID(product_id);
				ServletOutputStream out = response.getOutputStream();
				out.write(img);
				out.close();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		StoreVO storevo = _gson.fromJson(request.getReader().readLine(), StoreVO.class);
		Integer store_id = storevo.getStore_id();
//		System.out.println(store_id);

		List<StoreVO> list = storeSvc.findStorepageByStoreId(store_id);
//		for(StoreVO vo : list) {
//			System.out.println(vo);
//		}
//		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(list));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
