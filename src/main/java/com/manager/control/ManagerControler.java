package com.manager.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
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

import com.forum.model.service.impl.ForumServiceImpl;
import com.forum_message.model.Forum_messageService;
import com.forum_message.model.Forum_messageVO;
import com.forum_report.model.service.impl.*;
import com.forum_report.model.Forum_reportVO;
import com.google.gson.Gson;
import com.manager.model.ManagerVO;
import com.manager.model.service.ManagerService;
import com.manager.model.service.impl.*;
import com.member.service.impl.MemberServiceImpl;
import com.member.vo.MemberVO;
import com.message_report.model.Message_reportVO;
import com.message_report.model.service.impl.*;
import com.product.model.ProductVO;
import com.product.service.impl.ProductServiceImpl;
import com.product_img.model.Product_imgService;
import com.util.SingletonBeans;
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
	private ManagerService managerService;
	private ProductServiceImpl productService;
	private Product_imgService productImage;

	@Override
	public void init() throws ServletException {
		this.memberService = SingletonBeans.getInstance("MemberServiceImpl", MemberServiceImpl.class);
		this.message_reportService = SingletonBeans.getInstance("Message_reportServiceImpl",
				Message_reportServiceImpl.class);
		this.forumMessage = SingletonBeans.getInstance("Forum_messageService", Forum_messageService.class);
		this.forumReport = SingletonBeans.getInstance("Forum_reportServiceImpl", Forum_reportServiceImpl.class);
		this.forum = SingletonBeans.getInstance("ForumServiceImpl", ForumServiceImpl.class);
		this.managerService = SingletonBeans.getInstance("ManagerService", ManagerServiceImpl.class);
		this.productService = SingletonBeans.getInstance("ProductServiceImpl", ProductServiceImpl.class);
		this.productImage = SingletonBeans.getInstance("Product_imgService", Product_imgService.class);
		this.datas = 5;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet.action " + req.getParameter("action"));
		this.logout(req, resp);
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
			case "updateAdmin":
				updateAdmin(req, resp);
				break;
			case "updateAdminPassword":
				updateAdminPassword(req, resp);
				break;
			default:
				resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
				System.out.println("action " + action);
			}
			resp.sendRedirect(req.getHeader("referer"));
			return; // 預設回到上一頁
		} else {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		}
	}

	private void updateAdminPassword(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String newPassword = req.getParameter("newPassword");
		ManagerVO vo = (ManagerVO) req.getSession().getAttribute("admin");
		if (this.managerService.updatePassword(vo.getManager_id(), newPassword)) {
			resp.sendRedirect(req.getContextPath() + "/admin/console/administrators.jsp");
			return;
		}
	}

	private void updateAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String adminId = req.getParameter("adminId");
		String adminStatus = req.getParameter("adminStatus");
		PrintWriter out = resp.getWriter();
		try {
			Integer id = Integer.parseInt(adminId);
			Integer status = Integer.parseInt(adminStatus);
			if (this.managerService.updateStatus(id, status)) {
				out.print(true);
				return;
			} else {
				out.print(false);
				return;
			}
		} catch (Exception e) {
			out.print(false);
			e.printStackTrace();
			return;
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	private void changeFrontImg(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Part part = req.getPart("img");
		if (part.getContentType() != null) {
			String path = super.getServletContext().getRealPath("/") + "img\\"+ req.getParameter("filename") + ".jpeg";
			try (InputStream in = part.getInputStream();
					FileOutputStream out = new FileOutputStream(path)) {
//				"+super.getServletContext().getRealPath("/") + "img\\"+ req.getParameter("filename") + ".jpeg");
//				C:\JavaFramework\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\TGA103G1\img\1.jpeg
//				C:/JavaFramework/eclipse-workspace/TGA103G1/src/main/webapp/img/
				System.out.println(new File(super.getServletContext().getRealPath("/") + "img\\"
						+ req.getParameter("filename") + ".jpeg").getAbsolutePath());
//				System.out.println(req.getLocalAddr());
				in.transferTo(out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void forgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String user = req.getParameter("user");
		String birthday = req.getParameter("birthday");
		try (PrintWriter out = resp.getWriter();) {
			LocalDate localDate = Apadter.stringToLocalDate(birthday);
			ManagerVO vo = this.managerService.forgetPassword(user, localDate);
			if (vo == null) {
				out.print(false);
				return;
			} else {
				new Thread(new MailService(vo.getAccount(), "BarJarJo會員密碼", "您的密碼為:", vo.getPassword())).start();
				out.print(true);
				return;
			}
		}
	}

	private void searchForProduct(HttpServletRequest req, HttpServletResponse resp) {
		String productId = req.getParameter("productId");
		try {
			Integer id = Integer.parseInt(productId);
			List<ProductVO> list = List.of(this.productService.getOneProduct(id));
			req.getSession().setAttribute("products", list);
		} catch (NumberFormatException e) {
			req.getSession().setAttribute("products", this.productService.getAll());
		}
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
			e.printStackTrace();
		}
	}

	private void updateProdcut(HttpServletRequest req, HttpServletResponse resp) {
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
			}
		} catch (NumberFormatException e) {
			System.out.println("control.updateProdcut 數字格式不府和");
		}
	}

	private void roductStatusSelect(HttpServletRequest req, HttpServletResponse resp) {
		String productStatus = req.getParameter("productStatus");
		Integer status = Integer.parseInt(productStatus);
		req.getSession().removeAttribute(productStatus);
		req.getSession().setAttribute("productStatus", status);

	}

	private void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try (PrintWriter out = resp.getWriter()) {
			Integer id = Integer.parseInt(req.getParameter("adminId"));
			if (this.managerService.delete(id)) {
				req.getSession().setAttribute("admins", this.managerService.getAll());
				out.print("成功刪除");
			} else {
				out.print("刪除失敗");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("control.deleteAdmin 輸入的不是數字");
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accountString = req.getParameter("account");
		String passwordString = req.getParameter("password");
		String birthdayString = req.getParameter("birthday");
		if (this.managerService.insert(accountString, passwordString, Apadter.stringToLocalDate(birthdayString))) {
			sessionSetAttribute(req.getSession());
			resp.sendRedirect("/TGA103G1/admin/console/administrators.jsp");
		} else {
			System.out.println("control.register fail");
		}

	}

	private void deleteForum(HttpServletRequest req, HttpServletResponse resp) {
		String report = req.getParameter("reportId");
		String reason = req.getParameter("reason");
		String memberId = req.getParameter("memberId");
		try {
			Integer forumId = Integer.parseInt(report);
			this.forum.blockade(forumId);
			List<Integer> ids = this.forumReport.getForunIds(forumId);
			ArrayList<Forum_reportVO> list = (ArrayList<Forum_reportVO>) req.getSession().getAttribute("forumReport");
			for (int x = 0; x < ids.size(); x++) {
				for (int y = 0; y < list.size(); y++) {
					if (ids.get(x).equals(list.get(y).getForum_report_id())) {
						list.remove(y);
						break;
					}
				}
			}
			Integer member = Integer.parseInt(memberId);
			MemberVO member1 = this.memberService.findByPrimaryKey(member);
			req.getSession().setAttribute("forumReport", list);
			new Thread(new MailService(member1.getEmail(), "屏蔽文章", member1.getNickname() + ",", reason)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		resp.sendRedirect(req.getHeader("referer"));
	}

	private void seenForum(HttpServletRequest req, HttpServletResponse resp) {
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
			}
			req.getSession().setAttribute("forumReport", list);
		} catch (Exception e) {
			System.out.println("control.seenforum numberformat fail");
		}

	}

	private void updateforumReport(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer btnVal = Integer.parseInt(req.getParameter("btnVal"));
			this.message_reportService.update(btnVal);
			HttpSession session = req.getSession();
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) session
					.getAttribute("messageReportList");
			for (int x = 0; x < list.size(); x++) {
				if (list.get(x).get("message_id").equals(btnVal)) {
					list.remove(x);
					session.setAttribute("messageReportList", list);
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 刪除指定的文章留言"們" deleteVal=forumMessageId btnVal=reportId reason=reason
	 */
	private void deleteForumMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String deleteVal = req.getParameter("deleteVal");
		String btnVal = req.getParameter("btnVal");
		String reason = req.getParameter("reason");
		PrintWriter out = resp.getWriter();
		try {
			Integer forumMessageId = Integer.parseInt(deleteVal);
			Integer reportId = Integer.parseInt(btnVal);
			List<Integer> list = this.message_reportService.getMessageReportByMessageId(forumMessageId);
			List<HashMap<String, Object>> messageReports = (List<HashMap<String, Object>>) req.getSession()
					.getAttribute("messageReportList");
			for (int x = 0; x < list.size(); x++) {
				for (int y = 0; y < messageReports.size(); y++) {
					if (list.get(x).equals(messageReports.get(y).get("message_report_id"))) {
						messageReports.remove(y);
						break;
					}
				}
			}
			MemberVO vo = this.message_reportService.getMemberByMessageReportId(reportId);
			new Thread(new MailService(vo.getEmail(), "留言遭檢舉刪除", ", " + vo.getNickname(), reason)).start();
			req.getSession().setAttribute("messageReportList", messageReports);
			out.print(123);
			return;
		} catch (Exception e) {
			out.print(123);
			e.printStackTrace();
			return;
		} finally {
			if (out != null)
				out.close();
		}
	}

	private void getFroumMessage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			Integer index = Integer.parseInt(req.getParameter("btnVal"));
			Forum_messageVO msg = this.forumMessage.get(index);
			Map<String, Object> map = new HashMap<>();
			map.put("message_id", msg.getMessage_id());
			map.put("member_id", msg.getMember_id());
			map.put("forum_id", msg.getForum_id());
			map.put("context", msg.getContent());
			map.put("date", Apadter.localDateTimeToDate(msg.getDate()).getTime());
			out.print(new Gson().toJson(map));
//			return;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("context", "找不到文章");
			out.print(new Gson().toJson(map));
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private void cancelSearch(HttpServletRequest req, HttpServletResponse resp) {
		List<MemberVO> memberList = this.memberService.getAll();
		HttpSession session = req.getSession();
		session.setAttribute("members", memberList);
		session.setAttribute("memberPages",
				memberList.size() % datas == 0 ? memberList.size() / datas : memberList.size() / datas + 1);
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

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie = new Cookie("barjarjo", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer idInteger = Integer.parseInt(req.getParameter("member_id"));
			Integer permissionInteger = Integer.parseInt(req.getParameter("permission"));
			if (memberService.updatePermission(idInteger, permissionInteger)) {
				req.getSession().setAttribute("members", memberService.getAll());
			}
		} catch (NumberFormatException e) {
			System.out.println("ManagerControler.update fail");
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("rand");
		if (code != null && rand != null) {
			if (code.equals(rand)) {
				String user = req.getParameter("user");
				String password = req.getParameter("password");
				ManagerVO vo = this.managerService.login(user, password);
				if (vo != null) {
					session.setAttribute("admin", vo);
					sessionSetAttribute(session);
					String remeberMe = req.getParameter("remeberMe");
					if ("on".equals(remeberMe)) {
						Cookie cookie = new Cookie("barjarjo",
								Base64.getEncoder().encodeToString(JsonUtil.managerToString(vo).getBytes()));
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

	private void sessionSetAttribute(HttpSession session) {
		List<MemberVO> memberList = this.memberService.getAll();
		session.setAttribute("members", memberList);
		session.setAttribute("memberPages",
				memberList.size() % datas == 0 ? memberList.size() / datas : memberList.size() / datas + 1);

		List<Object[]> objlist = this.message_reportService.getAllAndForumId();
		List<Map<String, Object>> messageReportList = new ArrayList<>();
		for (int x = 0; x < objlist.size(); x++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message_report_id", objlist.get(x)[0]);
			map.put("member_id", objlist.get(x)[1]);
			map.put("message_id", objlist.get(x)[2]);
			map.put("reason", objlist.get(x)[3]);
			map.put("date", objlist.get(x)[4]);
			map.put("status", objlist.get(x)[5]);
			map.put("forum_id", objlist.get(x)[6]);
			messageReportList.add(map);
		}

		session.setAttribute("messageReportList", messageReportList);
		session.setAttribute("datas", datas);
		List<ManagerVO> list = this.managerService.getAll();
		session.setAttribute("admins", list);
		ArrayList<ProductVO> products = (ArrayList<ProductVO>) this.productService.getAll();
		int listing = 0;
		for (ProductVO tmp : products) {
			if (tmp.getStatus().equals(1))
				++listing;
		}
		session.setAttribute("listing", listing);
		session.setAttribute("products", products);
		session.setAttribute("productStatus", 3);
		session.setAttribute("forumReport", this.forumReport.getAll());
	}

	private void requestSetError(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("error", errorMessage);
		req.setAttribute("url", req.getHeader("referer"));
		req.getRequestDispatcher("/admin/console/error.jsp").forward(req, resp);
	}
}
