package com.store.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;




@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
	
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數**********************/
			String account = req.getParameter("account");
			String password = req.getParameter("passwrd");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			String address = req.getParameter("city")+req.getParameter("dist")+req.getParameter("address");
			Integer store_type = Integer.valueOf(req.getParameter("store_type"));
			String dayoff = req.getParameter("dayoff");
			String open = req.getParameter("open");
			String end = req.getParameter("end");
			StoreVO vo = new StoreVO();
			vo.setAccount(account);
			vo.setPassword(password);
			vo.setPhone(phone);
			vo.setEmail(email);
			vo.setAddress(address);
			vo.setStore_type(store_type);
			vo.setDayoff(dayoff);
			vo.setWork_open(open);
			vo.setWork_end(end);
			
			
			if(account == null || (account.trim()).length() == 0) {
				errorMsgs.put("account", "請輸入文字");
			}
			if(password == null || (password.trim()).length() == 0) {
				errorMsgs.put("password", "請輸入文字");
			}
			String phoneRex = "\\d{2}-\\d{8}";
			if(phone == null || (phone.trim()).length() == 0) {
				errorMsgs.put("phone", "請輸入文字");
			}else if(!phone.trim().matches(phoneRex)) {
				errorMsgs.put("phone", "請符 xx-xxxxxxxx 格式");
			}
			


			String openRex = "\\d{2}\\:\\d{2}";
			if(open == null || (open.trim()).length() == 0) {
				errorMsgs.put("open", "請輸入文字");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("open", "請符 xx:xx 格式");
			}
			
	
			String closeRex = "\\d{2}\\:\\d{2}";
			if(end == null || (end.trim().length() == 0)) {
				errorMsgs.put("close", "請輸入文字");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("close", "請符 xx:xx 格式");
			}
			
			
			if(dayoff == null || (dayoff.trim().length() == 0)) {
				errorMsgs.put("dayoff", "請輸入文字");
			}
			/***************************2.開始新增資料***************************************/
			StoreService service = new StoreService() ;
			service.addStore(vo);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/product/productlist.jsp";
			req.getSession().setAttribute("store", account);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
	}
	
}
