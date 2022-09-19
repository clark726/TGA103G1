package com.forum_report.model;

import java.util.List;

public class Forum_reportService {
	private Forum_reportJNDI dao;
	public Forum_reportService() {
		this.dao = new Forum_reportJNDI();
	}
	public boolean update(Integer id) {
		return this.dao.update(id);
	}
	public List<Forum_reportVO> getAll(){
		return this.dao.getAll();
	}
}
