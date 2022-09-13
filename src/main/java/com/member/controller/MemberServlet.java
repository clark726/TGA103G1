package com.member.controller;

import java.io.IOException;
import java.sql.*;
import java.time.*;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.sql.*;

import com.manager.model.*;
import com.member.model.MemberService;

@WebServlet("/control")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			switch (action) {
			case "login":
				login(req, resp);
				break;
			case "update":
				update(req, resp);
				break;
			case "logout":
				logout(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/login.jsp");
				break;
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath() + "/login.jsp");
		return;
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberService();
		service.update(Integer.parseInt(req.getParameter("member_id")), req.getParameter("account"),
				req.getParameter("password"), LocalDate.parse(req.getParameter("birthday")),
				req.getParameter("address"), Integer.parseInt(req.getParameter("gender")), req.getParameter("email"),
				req.getParameter("nickname"), req.getParameter("phone"),
				LocalDateTime.parse(req.getParameter("register")), Integer.parseInt(req.getParameter("permission")));
		req.getSession().setAttribute("members", service.getAll());
		resp.sendRedirect(req.getContextPath() + "/admin/admin.jsp");
		return;
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerService jndi = new ManagerService();
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("rand");
		if (code != null && rand != null) {
			if (code.equals(rand)) {
				String user = req.getParameter("user");
				String password = req.getParameter("password");
				if (jndi.check(user, password)) {
					session.setAttribute("id", user);
					session.setAttribute("members", new MemberService().getAll());
					resp.sendRedirect(req.getContextPath() + "/admin/admin.jsp");
					return;
				}
			}
		}
		req.setAttribute("error", "123");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		return;
	}
}
