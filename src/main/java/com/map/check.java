package com.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.vo.MemberVO;

@WebServlet("/checkk")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO vo = (MemberVO) req.getSession().getAttribute("userid");
		
		try (PrintWriter pw = resp.getWriter();){
			if (vo != null) {
				pw.print(vo.getMember_id());
			} else {
//				Gson gson = new Gson();
//				pw.print(gson.toJson(new HashMap<String,String>().put("url", "/TGA103G1/front-end/member/jsp/login.jsp")));
//				pw.print();
//				resp.sendRedirect("/TGA103G1/front-end/member/jsp/login.jsp");
				pw.print(vo);
			}
			
		} catch (Exception e) {}
	}
}
