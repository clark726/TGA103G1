package com.member.service;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberService {
	
	boolean update(MemberVO member);
	
	boolean register(MemberVO member);
	
//	MemberVO login(MemberVO member);
	
	MemberVO findByPrimaryKey(Integer member_id);
	
	MemberVO selectByUsername(String account);
	
	List<MemberVO> getAll();

	boolean login(String account, String password);
	
	boolean updatePermission(Integer id,Integer permission);
	
	boolean updatePassword(MemberVO member);
}
