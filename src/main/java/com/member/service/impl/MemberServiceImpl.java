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

}
