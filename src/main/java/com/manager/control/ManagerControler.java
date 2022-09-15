package com.manager.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet(value = "/control", loadOnStartup = 100)
public class ManagerControler extends HttpServlet {
	MemberService service = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
//		resp.setContentType("text/html;charset=UTF-8");
//		PrintWriter out =  resp.getWriter();
//		out.print("<h1>Error</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		req.getSession().removeAttribute("error");
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
			case "search":
				search(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
				System.out.println(action);
//				return;
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
//			return;
		}
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));
			this.service = new MemberService();
			List<MemberVO> list = new ArrayList<>();
			list.add(service.get(member_id));
			req.getSession().removeAttribute("members");
			req.getSession().setAttribute("members", list);
			req.getSession().setAttribute("pages", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
			resp.sendRedirect(req.getContextPath() + "/admin/console/admin.jsp?page=0");
		} catch (Exception e) {
			req.getSession().setAttribute("error", "not found");
			resp.sendRedirect(req.getContextPath() + "/admin/console/admin.jsp?page=0");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
//		return;
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service = new MemberService();
		service.update(Integer.parseInt(req.getParameter("member_id")), req.getParameter("account"),
				req.getParameter("password"), LocalDate.parse(req.getParameter("birthday")),
				req.getParameter("address"), Integer.parseInt(req.getParameter("gender")), req.getParameter("email"),
				req.getParameter("nickname"), req.getParameter("phone"),
				LocalDateTime.parse(req.getParameter("register")), Integer.parseInt(req.getParameter("permission")));
		req.getSession().setAttribute("members", service.getAll());
		resp.sendRedirect(req.getContextPath() + "/admin/console/admin.jsp?page=0");
//		return;
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerService service = new ManagerService();
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("rand");
		if (code != null && rand != null) {
			if (code.equals(rand)) {
				String user = req.getParameter("user");
				String password = req.getParameter("password");
				if (service.check(user, password)) {
					session.setAttribute("admin", user);
					List<MemberVO> list = new MemberService().getAll();
					session.setAttribute("members", list);
					session.setAttribute("pages", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
					resp.sendRedirect(req.getContextPath() + "/admin/console/admin.jsp?page=0");
					return;
				}
			}
		}
		session.setAttribute("error", "123");
		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
//		return;
	}
}
