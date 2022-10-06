package com.member.dao;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberDao {
	
	Integer insert(MemberVO member);
	
	boolean login(String account,String password);

	boolean update(MemberVO member);
	
	MemberVO selectByUsername(String username);
		
	List<MemberVO> getAll();

	boolean updatePermission(Integer id, Integer permission);

	MemberVO findByPrimaryKey(Integer member_id);
	
	boolean updatePassword(MemberVO member);
	
	MemberVO selectForPass(String account,String email);

	boolean updatePassByUsername(MemberVO member);
}
