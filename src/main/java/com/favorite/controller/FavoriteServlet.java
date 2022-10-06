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

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteService service;
	private Gson _gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String memId = request.getParameter("member_id");
		String stoId = request.getParameter("store_id");
		if ("getImg".equals(action)) {
			if (memId != null) {
				Integer member_id = Integer.parseInt(memId);
				Integer store_id = Integer.parseInt(stoId);

				FavoriteVO vo = service.getStoreImgByStoreId(member_id, store_id);

				ServletOutputStream out = response.getOutputStream();
				System.out.println(vo.getImg());
				byte[] img = vo.getImg();
				out.write(img);
				out.close();
			}
		}
	}

	@Override
	public void init() throws ServletException {
		service = new FavoritServiceIpml();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MemberVO member = (MemberVO) request.getSession().getAttribute("userid");

		List<FavoriteVO> list = new ArrayList<FavoriteVO>();
		list.add((FavoriteVO) service.getAll(member.getMember_id()));
		response.setContentType("application/json");

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
