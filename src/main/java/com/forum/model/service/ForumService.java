package com.forum.model.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.forum.model.ForumVO;

public interface ForumService {
	public List<ForumVO> getAll();
	public ForumVO get(Integer forum_id);
	public boolean add(ForumVO obj);
	public boolean update(ForumVO obj);
	public boolean delete(Integer id);
	 public boolean activation(Integer forumId,Integer status);
	 public boolean blockade(Integer forumId);
}
