package com.member.model;

import java.time.*;
import java.util.List;

public class MemberService {
	private MemberJNDIDAO dao ;

	public MemberService() {
		dao = new MemberJNDIDAO();
	}
	
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public void delete(Integer id) {
		dao.delete(id);
	}
	
	public MemberVO get(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	
	public MemberVO insert(String account,String password,LocalDate birthday,String address,Integer gender,String email,String nickname,String phone,Integer permission) {
		MemberVO result = new MemberVO(account,password,birthday,address,gender,email,nickname,phone,permission);
		dao.insert(result);
		return result;
	}
	
	public MemberVO update(Integer member_id, String account, String password, LocalDate birthday, String address, Integer gender,
            String email, String nickname, String phone, LocalDateTime register, Integer permission) {
		MemberVO result = new MemberVO(member_id,account,password,birthday,address,gender,email,nickname,phone,register,permission);
		dao.update(result);
		return result;
	}
}
