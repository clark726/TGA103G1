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
import com.store.service.StoreService;
import com.store.service.impl.StoreServiceImpl;

@WebServlet("/VerifyAccount")
public class VerifyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private StoreService StoreSvc = new StoreServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StoreVO store = _gson.fromJson(request.getReader().readLine(), StoreVO.class);

		// 判斷是否重複
		if (StoreSvc.findStoreAccount(store.getAccount()) != null) {
			store.setSuccessful(false);
			store.setMessage("帳號重複");
		} else {
			store.setSuccessful(true);
			store.setMessage("此帳號可以使用");
		}

		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(store));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
