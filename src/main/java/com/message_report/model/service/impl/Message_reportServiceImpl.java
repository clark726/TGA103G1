package com.message_report.model.service.impl;

import java.util.List;

import com.message_report.model.Message_reportJNDIDAO;
import com.message_report.model.Message_reportVO;
import com.message_report.model.service.Message_reportService;

public class Message_reportServiceImpl implements Message_reportService{
	private Message_reportJNDIDAO jndi;
	public Message_reportServiceImpl() {
		this.jndi = new Message_reportJNDIDAO();
	}
	public List<Message_reportVO> getAll(){
		return jndi.getAll();
	}
	public boolean update(Integer id) {
		return jndi.update(id);
	}
}
