package com.favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorite.model.FavoriteVO;
import com.favorite.service.FavoriteService;
import com.favorite.service.impl.FavoritServiceIpml;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.vo.MemberVO;

@WebServlet("/FindFavorite")
public class FindFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson _gson = new Gson();
	private FavoriteService service = new FavoritServiceIpml();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("userid");
		List<FavoriteVO> list = service.getAll(memberVO.getMember_id());
		
		try(PrintWriter pw = response.getWriter()){
			pw.print(new GsonBuilder().create().toJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
