package com.member.model;

import java.util.List;

public interface MemberDAO {
	public void insert(MemberVO obj);
    public void update(MemberVO obj);
    public void delete(Integer id);
    public MemberVO findByPrimaryKey(Integer id);
    public List<MemberVO> getAll();
}
