package com.member.dao;


import com.member.vo.MemberVO;

public interface MemberDao {
	Integer insert(MemberVO member);
	
	MemberVO login(MemberVO member);

	boolean update(MemberVO member);
	
	MemberVO selectByUsername(String username);
}
