package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.layout.PatternLayout;

import com.forum.model.ForumJNDI;

/**
 * Servlet implementation class DeleteMyContent
 */
@WebServlet("/DeleteMyContent")
public class DeleteMyContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		String id = req.getParameter("forumId"); //拿到ajax拿到的參數
		PrintWriter out = res.getWriter(); 		//取得回傳訊息的物件
		try {												   //然後該錯誤處理的要處理
			if(new ForumJNDI().delete(Integer.parseInt(id))) { //記得該轉型的要轉型
				out.print("刪除成功");
			}else {
				out.print("刪除失敗");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print(false);
		}finally {
			if(out != null) out.close();
		}
	}

}
