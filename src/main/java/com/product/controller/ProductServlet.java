package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_img.model.Product_imgVO;
import com.product_img.model.Product_imgVOJDBC;

@WebServlet("/ProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer p_type = Integer.valueOf(req.getParameter("p_type"));

			String p_name = req.getParameter("p_name");
			if (p_name == null || p_name.trim().length() == 0) {
				errorMsgs.put("p_name", "請勿空白");
			}

			Integer p_price = null;
			try {
				p_price = Integer.valueOf(req.getParameter("p_price").trim());
			} catch (NumberFormatException e) {
				p_price = 0;
				errorMsgs.put("p_price", "請填數字");
			}

			Integer p_stock = null;
			try {
				p_stock = Integer.valueOf(req.getParameter("p_stock").trim());
			} catch (NumberFormatException e) {
				p_stock = 0;
				errorMsgs.put("p_stock", "請填數字");
			}
			String p_produce = req.getParameter("p_produce");
			if (p_produce == null || p_produce.trim().length() == 0) {
				errorMsgs.put("p_produce", "請勿空白");
			}

			ProductVO productVO = new ProductVO();
			productVO.setType_id(p_type);
			productVO.setName(p_name);
			productVO.setName(p_name);
			productVO.setPrice(p_price);
			productVO.setStock(p_stock);
			productVO.setDescription(p_produce);

			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			Collection<Part> parts = req.getParts();
			for(Part part : parts) {
				InputStream in = part.getInputStream();
				Product_imgVO img1 = new Product_imgVO();
				Product_imgVOJDBC img = new Product_imgVOJDBC();
				byte[] b = new byte[(int)in.available()];
				in.read(b);
				img1.setImg(b);
//				img1.setProduct_id();
				
			}
			
			/*************************** 2.開始新增資料 ***************************************/
			ProductService service = new ProductService();
			service.addProduct(p_name, p_price, 1, p_produce, p_type, p_stock);
			
		
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("productVO", service); // 資料庫取出的empVO物件,存入req

			String url = "/back-end/product/productlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {

			/*************************** 1.接收請求參數 ***************************************/
			Integer product_id = Integer.valueOf(req.getParameter("product_id"));
			/*************************** 2.開始刪除資料 ***************************************/
			ProductService productSvc = new ProductService();
			productSvc.delete(product_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/product/productlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer product_id = Integer.valueOf(req.getParameter("product_id").trim());
			
			/***************************2.開始查詢資料****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(product_id);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("productVO", productVO);         // 資料庫取出的productVO物件,存入req
			String url = "/back-end/product/updateproduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer product_id = Integer.valueOf(req.getParameter("product_id").trim());
			
			Integer p_type = Integer.valueOf(req.getParameter("p_type"));
			
			String p_name = req.getParameter("p_name");
			if (p_name == null || p_name.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}

			Integer p_price = null;
			try {
				p_price = Integer.valueOf(req.getParameter("p_price").trim());
			} catch (NumberFormatException e) {
				p_price = 0;
				errorMsgs.add("請填數字");
			}

			Integer p_stock = null;
			try {
				p_stock = Integer.valueOf(req.getParameter("p_stock").trim());
			} catch (NumberFormatException e) {
				p_stock = 0;
				errorMsgs.add("請填數字");
			}
			String p_produce = req.getParameter("p_produce");
			if (p_produce == null || p_produce.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			Integer p_status = Integer.valueOf(req.getParameter("p_status").trim());
			
			

			ProductVO productVO = new ProductVO();
			productVO.setProduct_id(product_id);
			productVO.setType_id(p_type);
			productVO.setName(p_name);
			productVO.setPrice(p_price);
			productVO.setStock(p_stock);
			productVO.setDescription(p_produce);
			productVO.setStatus(p_status);

			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/updateproduct.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			ProductService productSvc = new ProductService();
			productVO = productSvc.updatStore(product_id, p_name, p_price, 1, p_produce, p_type, p_stock , p_status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/product/productlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
	}

}
