package com.forum_report.model.service.impl;

import java.util.List;

import com.forum_report.model.*;
import com.forum_report.model.service.Forum_reportService;
import com.member.vo.MemberVO;

public class Forum_reportServiceImpl implements Forum_reportService{
	private Forum_reportJNDI dao;
	public Forum_reportServiceImpl() {
		this.dao = new Forum_reportJNDI();
	}
	public boolean update(Integer id) {
		return this.dao.update(id);
	}
	public List<Forum_reportVO> getAll(){
		return this.dao.getAll();
	}
	public List<Integer> getForunIds(Integer id){
		return this.dao.getFourmIds(id);
	}
	public Integer findForumId(Integer id) {
		return this.dao.findForumId(id);
	}
	@Override
	public MemberVO findMemberByForumId(Integer id) {
		return this.dao.findMemberByForumId(id);
	}

}
