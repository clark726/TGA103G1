package com.favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorite.model.FavoriteVO;
import com.favorite.service.FavoriteService;
import com.favorite.service.impl.FavoritServiceIpml;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.service.MemberService;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;
import com.store.model.StoreVO;

@WebServlet("/favoriteDelete")
public class FavoriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteService service;
	private Gson _gson = new Gson();


	@Override
	public void init() throws ServletException {
		service = new FavoritServiceIpml();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		FavoriteVO favoriteVO = _gson.fromJson(request.getReader().readLine(), FavoriteVO.class);
		service.delete(favoriteVO);
		
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(favoriteVO));

		} catch (Exception e) {
			e.printStackTrace();
		}
		

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
