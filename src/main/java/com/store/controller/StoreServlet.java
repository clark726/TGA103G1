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

import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;




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
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			String address = req.getParameter("city")+req.getParameter("dist")+req.getParameter("address");
			Integer theme_id = Integer.parseInt(req.getParameter("theme_id"));
			String dayoff = req.getParameter("dayoff");
			String open = req.getParameter("open");
			String end = req.getParameter("end");
			
			
			if(account == null || (account.trim()).length() == 0) {
				errorMsgs.put("account", "請勿空白");
			}
			if(password == null || (password.trim()).length() == 0) {
				errorMsgs.put("password", "請勿空白");
			}
			if(name == null || (name.trim()).length() == 0) {
				errorMsgs.put("name", "請勿空白");
			}
			String phoneRex = "\\d{10}";
			if(phone == null || (phone.trim()).length() == 0) {
				errorMsgs.put("phone", "請勿空白");
			}else if(!phone.trim().matches(phoneRex)) {
				errorMsgs.put("phone", "請輸入數字");
			}
			
			String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
			if(email == null || (email.trim()).length() == 0) {
				errorMsgs.put("email", "請勿空白");
			}else if(!email.trim().matches(emailRex)) {
				errorMsgs.put("email", "請輸入正確格式");
			}
			
			if(address == null || (address.trim()).length() == 0) {
				errorMsgs.put("address", "請勿空白");
			}
			
			if(dayoff == null || (dayoff.trim().length() == 0)) {
				errorMsgs.put("dayoff", "請勿空白");
			}
			String openRex = "\\d{2}\\:\\d{2}";
			if(open == null || (open.trim()).length() == 0) {
				errorMsgs.put("open", "請勿空白");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("open", "請符 xx:xx 格式");
			}
			String closeRex = "\\d{2}\\:\\d{2}";
			if(end == null || (end.trim().length() == 0)) {
				errorMsgs.put("end", "請勿空白");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("end", "請符 xx:xx 格式");
			}
			
			StoreVO vo = new StoreVO();
			vo.setAccount(account);
			vo.setPassword(password);
			vo.setPhone(phone);
			vo.setName(name);
			vo.setEmail(email);
			vo.setAddress(address);
			vo.setTheme_id(theme_id);
			vo.setDayoff(dayoff);
			vo.setWork_open(open);
			vo.setWork_end(end);
			req.setAttribute("StoreVO", vo); // 含有輸入格式錯誤的StoreVO物件,也存入req
			
	
			/***************************2.開始新增資料***************************************/
			StoreService service = new StoreServiceImpl() ;
			//判斷是否相同帳號
			if(service.addStore(vo) == false) {
				errorMsgs.put("error", "重複帳號");
			}else {
				service.addStore(vo);
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/storesumit.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/product/productlist.jsp";
			req.getSession().setAttribute("store", account);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
	}
	
}
