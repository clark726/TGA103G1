package com.member.dao;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberDao {
	Integer insert(MemberVO member);
	
	MemberVO login(MemberVO member);

	boolean update(MemberVO member);
	
	MemberVO selectByUsername(String username);
	
	List<MemberVO> getAll();
}
