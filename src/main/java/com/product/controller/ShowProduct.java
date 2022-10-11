package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductVO;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;

@WebServlet("/ShowProduct")
public class ShowProduct extends HttpServlet {

	private ProductService productSvc = new ProductServiceImpl();
	private StoreService storeSvc = new StoreServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		StoreVO store =  (StoreVO) session.getAttribute("store");
		List<ProductVO> Productlist =  productSvc.ShowStoreProduct(store.getAccount());
		StoreVO storeId = storeSvc.findStoreAccount(store.getAccount());
		
		session.setAttribute("Productlist", Productlist); 
		session.setAttribute("storeId", storeId);
		String url = "/back-end/product/productlist.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
		
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
