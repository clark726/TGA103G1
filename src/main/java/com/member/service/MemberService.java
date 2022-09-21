package com.member.service;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberService {
	
	MemberVO update(MemberVO member);
	
	boolean register(MemberVO member);
	
	MemberVO login(MemberVO member);
	
	MemberVO findByPrimaryKey(Integer member_id);
	
	List<MemberVO> getAll();
}
