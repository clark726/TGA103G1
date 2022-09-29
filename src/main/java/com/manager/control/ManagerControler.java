package com.manager.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forum.model.ForumVO;
import com.forum.model.service.impl.ForumServiceImpl;
import com.forum_message.model.Forum_messageService;
import com.forum_message.model.Forum_messageVO;
import com.forum_report.model.service.impl.*;
import com.forum_report.model.Forum_reportVO;
import com.google.gson.Gson;
import com.manager.model.service.impl.*;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;
import com.message_report.model.service.impl.*;

@WebServlet(value = "/control", loadOnStartup = 100)
public class ManagerControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberServiceImpl memberService;
	Message_reportServiceImpl message_reportService;
	Integer datas;
	Forum_messageService forumMessage;
	Forum_reportServiceImpl forumReport;
	ForumServiceImpl forum;

	@Override
	public void init() throws ServletException {
		try {
			this.memberService = new MemberServiceImpl();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.message_reportService = new Message_reportServiceImpl();
		this.forumMessage = new Forum_messageService();
		this.datas = 5;
		this.forumReport = new Forum_reportServiceImpl();
		this.forum = new ForumServiceImpl();
	}

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
			case "search":
				search(req, resp);
				break;
			case "cancelSearch":
				cancelSearch(req, resp);
				break;
			case "getFroumMessage":
				getFroumMessage(req, resp);
				break;
			case "deleteForumMessage":
				deleteForumMessage(req, resp);
				break;
			case "updateforumReport":
				updateforumReport(req, resp);
				break;
			case "getAllforumReport":
				getAllforumReport(req, resp);
				break;
			case "readForum":
				readForum(req,resp);
				break;
			case "seenForum":
				seenForum(req,resp);
				break;
			case "deleteForum":
				deleteForum(req,resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
				System.out.println(action);
			}
		} else {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		}
	}
	
	public void deleteForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String forumId = req.getParameter("forumId"); 
		String reportId = req.getParameter("reportId");
		this.forumReport.update(Integer.parseInt(reportId));
		PrintWriter out = resp.getWriter();
		 if(this.forum.blockade(Integer.parseInt(forumId))) {
			 out.print("成功刪除文章");
		 }else {
			out.print("失敗");
		}
	}
	
	public void seenForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String forumReport = req.getParameter("forumReport");
		PrintWriter out = resp.getWriter();
		if(this.forumReport.update(Integer.parseInt(forumReport))) {
			out.print("修改成功");
		}else {
			out.print("修改失敗");
		}
		out.close();
	}
	
	public void readForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String forumId = req.getParameter("forumId"); 
		Integer id = Integer.parseInt(forumId); 
		ForumVO forum = this.forum.get(id);
		resp.getWriter().print(forum.getContent());
	}

	public void getAllforumReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Map> list = new ArrayList<Map>();
		Gson gson = new Gson();
		List<Forum_reportVO> reports = this.forumReport.getAll();
		for (int x = 0; x < reports.size(); x++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("forum_report_id", reports.get(x).getForum_report_id());
			map.put("member_id", reports.get(x).getMember_id());
			map.put("forum_id", reports.get(x).getForum_id());
			map.put("reason", reports.get(x).getReason());
//			map.put("date", localDateTimeToDate(reports.get(x).getDate()).getTime());
			map.put("date", reports.get(x).getDate().toString());
			map.put("status", reports.get(x).getStatus());
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin", req.getSession().getAttribute("admin"));
		list.add(map);
		resp.getWriter().print(gson.toJson(list));
	}

	public void updateforumReport(HttpServletRequest req, HttpServletResponse resp) {
		Integer btnVal = Integer.parseInt(req.getParameter("btnVal"));
		this.message_reportService.update(btnVal);
		HttpSession session = req.getSession();
		List<Forum_messageVO> list = (ArrayList<Forum_messageVO>) session.getAttribute("messageReportList");
		if (list.remove(btnVal - 1) != null) {
			session.removeAttribute("messageReportList");
			session.setAttribute("messageReportList", list);
		}
	}

	public void deleteForumMessage(HttpServletRequest req, HttpServletResponse resp) {
		String deleteVal = req.getParameter("deleteVal");
		this.forumMessage.delete(Integer.parseInt(deleteVal));
		System.out.println(req.getParameter("reason"));
		updateforumReport(req, resp);
	}

	// 有時間試試看public改成private
	public void getFroumMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer index = Integer.parseInt(req.getParameter("btnVal"));
		Gson gson = new Gson();
		Forum_messageVO msg = this.forumMessage.get(index);
		Map<String, Object> map = new HashMap<>();
		map.put("message_id", msg.getMessage_id());
		map.put("member_id", msg.getMember_id());
		map.put("forum_id", msg.getForum_id());
		map.put("context", msg.getContext());
		map.put("date", localDateTimeToDate(msg.getDate()).getTime());
		resp.getWriter().print(gson.toJson(map));
	}

	public static java.util.Date localDateTimeToDate(LocalDateTime localDateTime) {
		return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	private void cancelSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sessionSetAttribute(req.getSession());
		resp.sendRedirect(req.getHeader("referer"));
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));
			MemberVO memberVO = memberService.findByPrimaryKey(member_id);
			if (memberVO != null) {
				List<MemberVO> list = new ArrayList<>();
				list.add(memberVO);
				req.getSession().removeAttribute("members");
				req.getSession().setAttribute("members", list);
				req.getSession().setAttribute("memberPages",
						list.size() % datas == 0 ? list.size() / datas : list.size() / datas + 1);
			} else {
				requestSetError(req, resp, "not found");
			}
			String url = req.getHeader("referer");
			if (url.matches(".+(page=)\\d+")) {
				url = url.substring(0, url.lastIndexOf("=") + 1).concat("0");
			}
			resp.sendRedirect(url);
		} catch (Exception e) {
			requestSetError(req, resp, "not found");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		memberService.update(new MemberVO(Integer.parseInt(req.getParameter("member_id")), req.getParameter("account"),
				req.getParameter("password"), LocalDate.parse(req.getParameter("birthday")),
				req.getParameter("address"), Integer.parseInt(req.getParameter("gender")), req.getParameter("email"),
				req.getParameter("nickname"), req.getParameter("phone"),
				LocalDate.parse(req.getParameter("register")), Integer.parseInt(req.getParameter("permission"))));
		req.getSession().setAttribute("members", memberService.getAll());
		resp.sendRedirect(req.getHeader("referer"));
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerServiceImpl service = new ManagerServiceImpl();
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("rand");
		if (code != null && rand != null) {
			if (code.equals(rand)) {
				String user = req.getParameter("user");
				String password = req.getParameter("password");
				if (service.check(user, password)) {
					session.setAttribute("admin", user);
					sessionSetAttribute(session);
					resp.sendRedirect(req.getContextPath() + "/admin/console/members.jsp");
					return;
				} else {
//					requestSetError(req, resp, "wrong account or password");
					req.setAttribute("error", "error account or password");
					req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("error", "error code");
				req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
			}
		} else {
			requestSetError(req, resp, "wrong code");
		}
	}

	public void sessionSetAttribute(HttpSession session) {
		List<MemberVO> memberList = this.memberService.getAll();
		session.setAttribute("members", memberList);
		session.setAttribute("memberPages",
				memberList.size() % datas == 0 ? memberList.size() / datas : memberList.size() / datas + 1);
		session.setAttribute("messageReportList", this.message_reportService.getAll());
		session.setAttribute("datas", datas);
	}

	public void requestSetError(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("error", errorMessage);
		req.setAttribute("url", req.getHeader("referer"));
		req.getRequestDispatcher("/admin/console/error.jsp").forward(req, resp);
	}
}
