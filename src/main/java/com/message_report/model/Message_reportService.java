package com.message_report.model;

import java.util.List;

public class Message_reportService {
	private Message_reportJNDIDAO jndi;
	public Message_reportService() {
		this.jndi = new Message_reportJNDIDAO();
	}
	public List<Message_reportVO> getAll(){
		return jndi.getAll();
	}
	public boolean update(Integer id) {
		return jndi.update(id);
	}
}
