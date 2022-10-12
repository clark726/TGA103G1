package com.member.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.common.MailThread;
import com.common.Ramdon;
import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;

	public MemberServiceImpl()  {
		dao = new MemberDaoImpl();
	}

	@Override
	public boolean register(MemberVO member) {

		final String account = member.getAccount();
		if (account == null || account.isEmpty()) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			return false;
		}
		final String password = member.getPassword();
		if (password == null || password.isEmpty()) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			return false;
		} else if (!(password.trim().matches("^\\w{4,15}$"))) {
			member.setMessage("請輸入4～15個字");
			member.setSuccessful(false);
			return false;
		}
		if (dao.selectByUsername(member.getAccount()) != null) {
			member.setMessage("帳號重複");
			member.setSuccessful(false);
			return false;
		}
		final LocalDate birthday = member.getBirthday();
		if (birthday == null) {
			member.setMessage("請輸入生日");
			member.setSuccessful(false);
			return false;
		}

		final Integer gender = member.getGender();
		if (gender == null) {
			member.setMessage("請勾選性別");
			member.setSuccessful(false);
			return false;
		}
		final String email = member.getEmail();
		if (email == null || email.isEmpty()) {
			member.setMessage("請輸入email");
			member.setSuccessful(false);
			return false;
		}

		final String phone = member.getPhone();
		if (phone == null || phone.isEmpty()) {
			member.setMessage("請輸入電話號碼");
			member.setSuccessful(false);
			return false;
		} else if (!(phone.trim().matches("^09\\d{8}$"))) {
			member.setMessage("請輸入正確手機格式");
			member.setSuccessful(false);
			return false;
		}

//		String phoneString = member.getPhone(), phone = "";
//		try {
//			phone = phoneString.trim();
//		} catch (NullPointerException e) {
//			member.setMessage("請輸入電話號碼");
//			member.setSuccessful(false);
//		}
//		if (!(phone.matches("^09\\d{8}$"))) {
//			member.setMessage("請輸入正確手機號碼");
//			member.setSuccessful(false);
//			return false;
//		}

		member.setMessage("註冊成功");
		member.setSuccessful(true);
		System.out.println(member);
		dao.insert(member);
		return true;
	}

	@Override
	public boolean login(String account, String password) {
		if (account == null || account.isEmpty()) {
			return false;
		}
		if (password == null || password.isEmpty()) {
			return false;
		}
		return dao.login(account, password);
	}

	@Override
	public boolean update(MemberVO member) {
		final String email = member.getEmail();
		if (email == null || email.isEmpty()) {
			member.setMessage("請輸入電子信箱");
			member.setSuccessful(false);
			return false;
		}

		final String phone = member.getPhone();
		if (phone == null || phone.isEmpty()) {
			member.setMessage("請輸入電話號碼");
			member.setSuccessful(false);
			return false;
		} else if (!(phone.trim().matches("^09\\d{8}$"))) {
			member.setMessage("請輸入電話號碼");
			member.setSuccessful(false);
			return false;
		}
		return dao.update(member);
	}

	@Override
	public MemberVO findByPrimaryKey(Integer member_id) {
		return dao.findByPrimaryKey(member_id);
	}

	@Override
	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean updatePermission(Integer id, Integer permission) {
		if (id < 0 || permission<0 || permission>2) {
			return false;
		}else {
			return dao.updatePermission(id,permission);
		}
	}

	@Override
	public MemberVO selectByUsername(String account) {
		
		if(account != null) {
			return dao.selectByUsername(account);
		}else {
			return null;
		}
	}

	@Override
	public boolean updatePassword(MemberVO member) {
		return dao.updatePassword(member);
	}

	@Override
	public MemberVO selectForPass(String account, String email) {
		MemberVO member = new MemberVO();
		if(account.equals("")) {
			member.setMessage("請輸入帳號");
			member.setSuccessful(false);
			return member;
		}
		if(email.equals("")) {
			member.setMessage("請輸入信箱");
			member.setSuccessful(false);
			return member;
		}
		return selectForPass(account, email);
	}
	public MemberVO checkCode(MemberVO member) {
		final String input = member.getInputCode();
		final String verification = member.getVerifyAccount();
		
		System.out.println(input);
		System.out.println(verification);
		
		if ("".equals(input)) {
			member.setMessage("驗證碼未輸入");
			member.setSuccessful(false);
			return member;
		}
		if (!input.equals(verification)) {
			member.setMessage("驗證碼輸入錯誤");
			member.setSuccessful(false);
			return member;
		}
		
		member.setSuccessful(true);
		return member;
	}
	
	public MemberVO forgetPassChange(MemberVO member) {
		final String newPass = member.getNewPassword();
		
		System.out.println(newPass);
		
		member.setPassword(newPass);
		
		if (dao.updatePassByUsername(member) == false) {
			member.setSuccessful(false);
			member.setMessage("密碼更改出現錯誤，請聯絡管理員!");
			return member;
		}
		
		member.setMessage("資料更改成功");
		member.setSuccessful(true);
		return member;
	}

	public MemberVO forgetPass(MemberVO member) {
		final String account = member.getAccount();
		final String email = member.getEmail();
		
		if ("".equals(account)) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			return member;
		}
		if ("".equals(email)) {
			member.setMessage("信箱未輸入");
			member.setSuccessful(false);
			return member;
		}
		if (dao.selectForPass(account, email) == null) {
			member.setSuccessful(false);
			member.setMessage("帳號或信箱錯誤！");
			return member;
		}
		
		// 讓信件可以抓到名字
		member.setAccount(dao.selectForPass(account, email).getAccount());
		// JavaMail執行緒
		MailThread.to = member.getEmail();
		MailThread.ch_name = member.getAccount();
		Ramdon code = new Ramdon();
		MailThread.passRandom = code.getRandom();
		member.setVerifyAccount(MailThread.passRandom);
		MailThread.messageText = "Hello! 會員帳號：" + MailThread.ch_name + " 您的驗證碼為: " + MailThread.passRandom + "\n" + "(30分鐘後過期)";
		MailThread jmt = new MailThread();
		jmt.start();
		
		member.setSuccessful(true);
		return member;
	}

}
