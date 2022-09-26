package com.member.dao;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberDao {
	
	Integer insert(MemberVO member);
	
	boolean login(String account,String password);

	boolean update(MemberVO member);
	
	MemberVO selectByUsername(String username);
	
	MemberVO findByPrimaryKey(Integer id);
	
	List<MemberVO> getAll();

	boolean updatePermission(Integer id, Integer permission);

	MemberVO findByPrimaryKey(Integer member_id);
	
	boolean updatePassword(MemberVO member);
}
