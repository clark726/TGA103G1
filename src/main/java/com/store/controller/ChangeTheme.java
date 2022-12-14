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
import com.store.model.StoreVO;
import com.store.service.impl.StoreServiceImpl;
import com.store_img.service.Store_imgService;
import com.store_img.service.impl.Store_imgServiceImpl;

@WebServlet("/ChangeTheme")
public class ChangeTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		StoreVO storeVO = gson.fromJson(req.getReader().readLine(), StoreVO.class);
		Integer theme_id = storeVO.getTheme_id();

		Store_imgService svc = new Store_imgServiceImpl();

		if (theme_id == 4) {
			List<StoreVO> list = svc.getStoreImg();
			res.setContentType("application/json");
			try (PrintWriter pw = res.getWriter()) {
				pw.print(new GsonBuilder().create().toJson(list));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			List<StoreVO> list = svc.getStoreImgByTheme(theme_id);
			res.setContentType("application/json");
			try (PrintWriter pw = res.getWriter()) {
				pw.print(new GsonBuilder().create().toJson(list));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
