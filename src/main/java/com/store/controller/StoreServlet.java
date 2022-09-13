package com.store.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String produce = req.getParameter("produce");
			if(produce == null || (produce.trim()).length() == 0) {
				errorMsgs.put("produce", "請輸入文字");
			}
			
			String open = req.getParameter("open");
			String openRex = "\\d{2}\\:\\d{2}";
			
			if(open == null || (open.trim()).length() == 0) {
				errorMsgs.put("open", "請輸入文字");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("open", "請符 xx:xx 格式");
			}
			
			String close = req.getParameter("close");
			String closeRex = "\\d{2}\\:\\d{2}";
			if(close == null || (close.trim().length() == 0)) {
				errorMsgs.put("close", "請輸入文字");
			}else if(!(open.trim().matches(openRex))) {
				errorMsgs.put("close", "請符 xx:xx 格式");
			}
			
			String dayoff = req.getParameter("dayoff");
			if(dayoff == null || (dayoff.trim().length() == 0)) {
				errorMsgs.put("dayoff", "請輸入文字");
			}
			/***************************2.開始新增資料***************************************/
//			StoreService service = new StoreService() ;
			
		}
	}
	
}
