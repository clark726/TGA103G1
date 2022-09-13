package com.forum_report.model;

import java.util.List;

public interface Forum_reportDAO  {

	public void insert(Forum_reportVO report);
	public void update(Forum_reportVO report);
	public void delete(Integer report_id);
	public Forum_reportVO findByPrimaryKey(Integer report_id);
	public List<Forum_reportVO>getAll();
}
