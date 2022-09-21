package com.member.service.impl;

import java.time.LocalDate;

import javax.naming.NamingException;

import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	
	
	public MemberServiceImpl() throws NamingException {
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
		String checkPs = "^[(a-zA-Z0-9)]{4,15}$";
		if (password == null || password.isEmpty()) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			return false;
		} else if (!(password.trim().matches(checkPs))) {
			member.setMessage("請輸入4～15個字元");
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

		final String nickname = member.getNickname();
		if (nickname == null || nickname.isEmpty()) {
			
			return false;
		}

		final String phone = member.getPhone();
		String checkPhone = "^[0]{1}[9]{1}\\d{8}$";
		if (phone == null || phone.isEmpty()) {
			member.setMessage("請輸入電話號碼");
			member.setSuccessful(false);
			return false;
		} else if (!(phone.trim().matches(checkPhone))) {
			member.setMessage("請輸入正確手機格式");
			member.setSuccessful(false);
			return false;
		}
		member.setMessage("註冊成功");
		member.setSuccessful(true);
		dao.insert(member);
		return true;
	}
	@Override
	public MemberVO login(MemberVO member) {
		final String account = member.getAccount();
		if(account == null || account.isEmpty()) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
		}
		
		final String password = member.getPassword();
		if(password == null || password.isEmpty()) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
		}

		return dao.login(member);
	}
	@Override
	public MemberVO update(MemberVO member) {
		Integer id = member.getMember_id();
		
		final String account = member.getAccount();
		if (account == null || account.isEmpty()) {

		}
		
		
		final Integer gender = member.getGender();
		if (gender == null) {
		}
		final String email = member.getEmail();
		if (email == null || email.isEmpty()) {
		}

		final String nickname = member.getNickname();
		if (nickname == null || nickname.isEmpty()) {
		}

		final String phone = member.getPhone();
		String checkPhone = "^[0]{1}[9]{1}\\d{8}$";
		if (phone == null || phone.isEmpty()) {
		} else if (!(phone.trim().matches(checkPhone))) {
		}
		dao.update(member);
		return member;
	}

}
