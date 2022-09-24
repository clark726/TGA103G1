package com.member.dao;


import java.util.List;

import com.member.vo.MemberVO;

public interface MemberDao {
	Integer insert(MemberVO member);
	
	Boolean login(String account,String password);

	boolean update(MemberVO member);
	
	MemberVO selectByUsername(String username);
	
	List<MemberVO> getAll();

	boolean updatePermission(Integer id, Integer permission);

	MemberVO findByPrimaryKey(Integer member_id);
}
