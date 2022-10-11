package com.forum_report.model.service;

import java.util.List;

import com.forum_report.model.Forum_reportVO;
import com.member.vo.MemberVO;

public interface Forum_reportService {
	public boolean update(Integer id);
	public List<Forum_reportVO> getAll();
	public MemberVO findMemberByForumId(Integer id);
}
