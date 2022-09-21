package com.member.service;


import com.member.vo.MemberVO;

public interface MemberService {
	
	MemberVO update(MemberVO member);
	
	boolean register(MemberVO member);
	
	MemberVO login(MemberVO member);

}
