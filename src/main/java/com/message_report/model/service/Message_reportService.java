package com.message_report.model.service;

import java.util.List;

import com.member.vo.MemberVO;
import com.message_report.model.Message_reportVO;

public interface Message_reportService {
	public List<Message_reportVO> getAll();
	public boolean update(Integer id);
	List<Integer> getMessageReportByMessageId(Integer messageId);
	public List<Integer> getForumIdByStatus(Integer id);
	public List<Object[]> getAllAndForumId();
	public MemberVO getMemberByMessageReportId(Integer id);
}
