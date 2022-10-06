package com.favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorite.service.impl.FavoritServiceIpml;
import com.favorite.model.FavoriteVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AllFavoriteM")
public class AllFavoriteM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		FavoriteVO favoriteVO = gson.fromJson(req.getReader().readLine(), FavoriteVO.class);
		Integer id =favoriteVO.getMember_id();
		FavoritServiceIpml svc = new FavoritServiceIpml();
		List<FavoriteVO> list = svc.getAllM(id);
	
		 res.setContentType("application/json");
		  try (PrintWriter pw = res.getWriter()) {
		   pw.print(new GsonBuilder().create().toJson(list));

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	}

}




