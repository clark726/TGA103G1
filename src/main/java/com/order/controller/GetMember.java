package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.vo.MemberVO;

@WebServlet("/GetMember")
public class GetMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("userid");

		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(_gson.toJson(memberVO));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
