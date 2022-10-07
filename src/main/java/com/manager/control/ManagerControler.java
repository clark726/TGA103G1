package com.manager.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.forum.model.ForumVO;
import com.forum.model.service.impl.ForumServiceImpl;
import com.forum_message.model.Forum_messageService;
import com.forum_message.model.Forum_messageVO;
import com.forum_report.model.service.impl.*;
import com.forum_report.model.Forum_reportVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manager.model.ManagerVO;
import com.manager.model.service.impl.*;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;
import com.message_report.model.Message_reportVO;
import com.message_report.model.service.impl.*;
import com.product.model.ProductVO;
import com.product.service.impl.ProductServiceImpl;
import com.product_img.model.Product_imgService;
import com.util.BeansFactory;
import com.util.JsonUtil;
import com.util.Apadter;

@WebServlet(value = "/control", loadOnStartup = 1)
@MultipartConfig
public class ManagerControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceImpl memberService;
	private Message_reportServiceImpl message_reportService;
	private Integer datas;
	private Forum_messageService forumMessage;
	private Forum_reportServiceImpl forumReport;
	private ForumServiceImpl forum;
	private ManagerServiceImpl managerService;
	private ProductServiceImpl productService;
	private Product_imgService productImage;

	@Override
	public void init() throws ServletException {
		this.memberService = BeansFactory.getInstance("MemberServiceImpl", MemberServiceImpl.class);
		this.message_reportService = BeansFactory.getInstance("Message_reportServiceImpl",
				Message_reportServiceImpl.class);
		this.forumMessage = BeansFactory.getInstance("Forum_messageService", Forum_messageService.class);
		this.forumReport = BeansFactory.getInstance("Forum_reportServiceImpl", Forum_reportServiceImpl.class);
		this.forum = BeansFactory.getInstance("ForumServiceImpl", ForumServiceImpl.class);
		this.managerService = BeansFactory.getInstance("ManagerServiceImpl", ManagerServiceImpl.class);
		this.productService = BeansFactory.getInstance("ProductServiceImpl", ProductServiceImpl.class);
		this.productImage = BeansFactory.getInstance("Product_imgService", Product_imgService.class);
		this.datas = 5;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			switch (action) {
//			case "watchimage":
//				watchimage(req, resp);
//				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
				System.out.println("doGet.action " + action);
			}
		} else {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			switch (action) {
			// 管理員登入
			case "login":
				login(req, resp);
				break;
			// 修改會員資料
			case "update":
				update(req, resp);
				break;
			// 管理員登出
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
				readForum(req, resp);
				break;
			case "seenForum":
				seenForum(req, resp);
				break;
			case "deleteForum":
				deleteForum(req, resp);
				break;
			// 新增管理員
			case "register":
				register(req, resp);
				break;
			// 刪除管理員
			case "deleteAdmin":
				deleteAdmin(req, resp);
				break;
			// onTheShelf 選擇器
			case "roductStatusSelect":
				roductStatusSelect(req, resp);
				break;
			case "getAllProduct":
				getAllProduct(req, resp);
				break;
			case "updateProdcut":
				updateProdcut(req, resp);
				break;
			case "getImageByProductId":
				getImageByProductId(req, resp);
				break;
			case "searchForProduct":
				searchForProduct(req, resp);
				break;
			case "forgetPassword":
				forgetPassword(req, resp);
				break;
			case "changeFrontImg":
				changeFrontImg(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
				System.out.println("action " + action);
			}
		} else {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		}
	}

	private void changeFrontImg(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if ("img".equals(part.getName()) && part.getContentType() != null) {
				try (InputStream in = part.getInputStream();
						FileOutputStream out = new FileOutputStream(super.getServletContext().getRealPath("/") + "img\\"
								+ req.getParameter("filename") + ".jpeg");) {
					in.transferTo(out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		File file = new File(super.getServletContext().getRealPath("/")+"img/"+req.getParameter("filename")+".jpeg");
//		System.out.println("file path: "+super.getServletContext().getRealPath("/")+"img/"+req.getParameter("filename")+".jpeg");
//		System.out.println("file exists: "+file.exists());
		resp.sendRedirect(req.getHeader("referer"));
	}

	private void forgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		String user = req.getParameter("user");
		String birthday = req.getParameter("birthday");
		try {
			LocalDate localDate = Apadter.stringToLocalDate(birthday);
			ManagerVO vo = this.managerService.forgetPassword(user, localDate);
			if (vo == null) {
				out.print(false);
				return;
			} else {
				new Thread(new MailService(vo.getAccount(), "BarJarJo會員密碼", "您的密碼為:", vo.getPassword())).start();
				out.print(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print(false);
		} finally {
			if (out != null)
				out.close();
		}
	}

	private void searchForProduct(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String productId = req.getParameter("productId");
		try {
			Integer id = Integer.parseInt(productId);
			List<ProductVO> list = List.of(this.productService.getOneProduct(id));
			req.getSession().setAttribute("products", list);
		} catch (NumberFormatException e) {
			req.getSession().setAttribute("products", this.productService.getAll());
		}
		resp.sendRedirect(req.getHeader("referer"));
		return;
	}

	public void getImageByProductId(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String productId = req.getParameter("productId");
		try (PrintWriter out = resp.getWriter();) {
			Integer id = Integer.parseInt(productId);
			byte[] img = this.productImage.findByProductID(id);
			String imgString = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(img);
			out.print(imgString);
		} catch (Exception e) {
		}
	}

	public void updateProdcut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			Integer status = Integer.parseInt(req.getParameter("status"));
			Integer id = Integer.parseInt(req.getParameter("productId"));
			if (this.productService.updateStatus(id, status)) {

				ArrayList<ProductVO> products = (ArrayList<ProductVO>) this.productService.getAll();
				int listing = 0;
				for (int x = 0; x < products.size(); x++) {
					if (products.get(x).getStatus() == 1) {
						listing++;
					}
				}
				req.getSession().setAttribute("listing", listing);
				req.getSession().setAttribute("products", this.productService.getAll());

				resp.sendRedirect(req.getHeader("referer"));
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("control.updateProdcut 數字格式不府和");
		}
		System.out.println("control.updateProdcut 修改資料失敗");
		resp.sendRedirect(req.getHeader("referer"));
	}

	public void getAllProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<ProductVO> list = this.productService.getAll();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		resp.getWriter().print(gson.toJson(list));
	}

	public void roductStatusSelect(HttpServletRequest req, HttpServletResponse resp) {
		String productStatus = req.getParameter("productStatus");
		Integer status = Integer.parseInt(productStatus);
		req.getSession().removeAttribute(productStatus);
		req.getSession().setAttribute("productStatus", status);
//		resp.getWriter().print(productStatus);

	}

	public void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try (PrintWriter out = resp.getWriter()) {
			Integer id = Integer.parseInt(req.getParameter("adminId"));
			if (this.managerService.delete(id)) {
				out.print("成功刪除");
			} else {
				out.print("刪除失敗");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("control.deleteAdmin 輸入的不是數字");
		}
	}

	public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accountString = req.getParameter("account");
		String passwordString = req.getParameter("password");
		String birthdayString = req.getParameter("birthday");
		if (this.managerService.insert(accountString, passwordString, Apadter.stringToLocalDate(birthdayString))) {
			sessionSetAttribute(req.getSession());
			resp.sendRedirect("/TGA103G1/admin/console/administrators.jsp");
		} else {
			System.out.println("control.register fail");
			resp.sendRedirect(req.getHeader("referer"));
		}

	}

	public void deleteForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String report = req.getParameter("reportId");
		try {
			Integer forumId = Integer.parseInt(report);
			this.forum.blockade(forumId);

			List<Integer> ids = this.forumReport.getForunIds(forumId);
			ArrayList<Forum_reportVO> list = (ArrayList<Forum_reportVO>) req.getSession().getAttribute("forumReport");

//			System.out.println("ids: " + ids);
//			System.out.println("forumReport: " + list);

			for (int x = 0; x < ids.size(); x++) {
				for (int y = 0; y < list.size(); y++) {
					if (ids.get(x).equals(list.get(y).getForum_report_id())) {
						list.remove(y);
						break;
					}
				}
			}

			req.getSession().setAttribute("forumReport", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect(req.getHeader("referer"));

//		String forumReport = req.getParameter("reportId");
//		try {
//			Integer reportId = Integer.parseInt(forumReport);
//			Integer forumId = this.forumReport.findForumId(reportId);
//			this.forum.blockade(forumId);
//			List<Integer> forumIds = this.forumReport.getForunIds(reportId);
//			ArrayList<Forum_reportVO> list = (ArrayList<Forum_reportVO>) req.getSession().getAttribute("forumReport");
//
//			for (int x = 0; x < forumIds.size(); x++) {
//				for (int y = 0; y < list.size(); y++) {
//					if (forumIds.get(x) == list.get(y).getForum_report_id()) {
//						System.out.println("beforeUpdate");
//						this.forumReport.update(forumIds.get(x));
//						System.out.println("beforeremove");
//						list.remove(y);
//						System.out.println("afterremove");
//					}
//				}
//			}
//			req.getSession().setAttribute("forumReport", list);
//			resp.sendRedirect(req.getHeader("referer"));
//			return;
//		} catch (Exception e) {
//			System.out.println("control.deleteForum fail");
//			resp.sendRedirect(req.getHeader("referer"));
//		}
	}

	public void seenForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String forumReport = req.getParameter("forumReport");
		try {
			ArrayList<Forum_reportVO> list = (ArrayList<Forum_reportVO>) req.getSession().getAttribute("forumReport");

			Integer forumId = Integer.parseInt(forumReport);
			if (this.forumReport.update(forumId)) {
				for (int x = 0; x < list.size(); x++) {
					if (list.get(x).getForum_report_id().equals(forumId)) {
						list.remove(x);
					}
				}
			} else {
				System.out.println("control.seenforum fail");
//				resp.sendRedirect(req.getHeader("referer"));
			}

			req.getSession().setAttribute("forumReport", list);
			resp.sendRedirect(req.getHeader("referer"));
		} catch (Exception e) {
			System.out.println("control.seenforum numberformat fail");
			resp.sendRedirect(req.getHeader("referer"));
		}

	}

	public void readForum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String forumId = req.getParameter("forumId");
		Integer id = Integer.parseInt(forumId);
		ForumVO forum = this.forum.get(id);
		resp.getWriter().print(forum.getContent());
	}

	// 做過修改但未測試
	public void getAllforumReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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

	private void updateforumReport(HttpServletRequest req, HttpServletResponse resp) {
		Integer btnVal = Integer.parseInt(req.getParameter("btnVal"));
		this.message_reportService.update(btnVal);
		HttpSession session = req.getSession();
		ArrayList<Message_reportVO> list = (ArrayList<Message_reportVO>) session.getAttribute("messageReportList");
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x).getMessage_id() == btnVal) {
				list.remove(x);
				session.setAttribute("messageReportList", list);
				break;
			}
		}
	}

	/**
	 * 刪除指定的文章留言"們" deleteVal=forumMessageId btnVal=reportId reason=reason
	 */
	private void deleteForumMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String deleteVal = req.getParameter("deleteVal");
		String btnVal = req.getParameter("btnVal");
		String reason = req.getParameter("reason");
		try (PrintWriter out = resp.getWriter()) {
			Integer forumMessageId = Integer.parseInt(deleteVal);
			Integer reportId = Integer.parseInt(btnVal);
			List<Integer> list = this.message_reportService.getMessageReportByMessageId(forumMessageId);
			List<Message_reportVO> messageReports = (List<Message_reportVO>) req.getSession()
					.getAttribute("messageReportList");

			for (int x = 0; x < list.size(); x++) {
				for (int y = 0; y < messageReports.size(); y++) {
					if (list.get(x).equals(messageReports.get(y).getMessage_report_id())) {
						messageReports.remove(y);
						break;
					}
				}
			}
			req.getSession().setAttribute("messageReportList", messageReports);
			out.print(123);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		new Thread(new MailService(vo.getEmail(), "留言遭刪除", vo.getAccount(), "刪除原因:" + req.getParameter("reason")))
//				.start();

//		req.getSession().setAttribute("messageReportList", this.message_reportService.getAll());
//		sessionSetAttribute(req.getSession());
//		resp.getWriter().print("true");
//		AsyncContext asyncContext = req.startAsync();
//		asyncContext.start(new MailService(asyncContext));

//		req.setAttribute("account", accountString);
//		if(req.isAsyncSupported()) {
//			AsyncContext asyncContext = req.getAsyncContext();
//			asyncContext.start(new MailService(asyncContext));
//		}else {
//			System.out.println("not asyncContext support cant send mail");
//		}
	}

	// 有時間試試看public改成private
	public void getFroumMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			Integer index = Integer.parseInt(req.getParameter("btnVal"));
			Gson gson = new Gson();
			Forum_messageVO msg = this.forumMessage.get(index);
			Map<String, Object> map = new HashMap<>();
			map.put("message_id", msg.getMessage_id());
			map.put("member_id", msg.getMember_id());
			map.put("forum_id", msg.getForum_id());
			map.put("context", msg.getContent());
			map.put("date", Apadter.localDateTimeToDate(msg.getDate()).getTime());
			out.print(gson.toJson(map));
		} catch (Exception e) {
			System.out.println("control.getFroumMessage");
			e.printStackTrace();
		}
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
		Integer idInteger = Integer.parseInt(req.getParameter("member_id"));
		Integer permissionInteger = Integer.parseInt(req.getParameter("permission"));
		if (memberService.updatePermission(idInteger, permissionInteger)) {
			req.getSession().setAttribute("members", memberService.getAll());
			resp.sendRedirect(req.getHeader("referer"));
		} else {
			System.out.println("ManagerControler.update fail");
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("rand");
//		System.out.println("remeberMe "+req.getParameter("remeberMe"));
		if (code != null && rand != null) {
			if (code.equals(rand)) {
				String user = req.getParameter("user");
				String password = req.getParameter("password");
				ManagerVO vo = this.managerService.login(user, password);
//				System.out.println("control.login.vo: "+vo);
				if (vo != null) {
					session.setAttribute("admin", vo);
					sessionSetAttribute(session);
					String remeberMe=req.getParameter("remeberMe");
					if("on".equals(remeberMe)) {
						System.out.println("remeber: "+remeberMe);
						Cookie cookie = new Cookie("barjarjo",Base64.getEncoder().encodeToString(JsonUtil.managerToString(vo).getBytes()));
						cookie.setMaxAge(604800);
						resp.addCookie(cookie);
					}
					resp.sendRedirect(req.getContextPath() + "/admin/console/admin.jsp?page=0");
					return;
				} else {
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
		List<ManagerVO> list = this.managerService.getAll();
		session.setAttribute("admins", list);
		ArrayList<ProductVO> products = (ArrayList<ProductVO>) this.productService.getAll();
		int listing = 0;
		for (int x = 0; x < products.size(); x++) {
			if (products.get(x).getStatus() == 1) {
				listing++;
			}
		}
		session.setAttribute("products", products);
		session.setAttribute("listing", listing);
		session.setAttribute("productStatus", 3);
		session.setAttribute("forumReport", this.forumReport.getAll());
	}

	public void requestSetError(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("error", errorMessage);
		req.setAttribute("url", req.getHeader("referer"));
		req.getRequestDispatcher("/admin/console/error.jsp").forward(req, resp);
	}
}
