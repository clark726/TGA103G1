package com.product.controller;

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
import com.product.model.ProductVO;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;

@WebServlet("/GetProductByTypeId")
public class GetProductByTypeId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private ProductService productSvc = new ProductServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<ProductVO> list = productSvc.getAll();
		
		response.setContentType("application/json");
		try(PrintWriter pw = response.getWriter()){
			pw.print(new GsonBuilder().create().toJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductVO productVO = _gson.fromJson(request.getReader().readLine(), ProductVO.class);
		Integer type_id = productVO.getType_id();
		List<ProductVO> list = productSvc.getProductBytypeId(type_id);

		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(list));
		} catch (Exception e) {
		}
	}

}
