package com.forum.model.service.impl;

import java.util.List;

import com.forum.model.ForumJNDI;
import com.forum.model.ForumVO;
import com.forum.model.service.ForumService;

public class ForumServiceImpl implements ForumService{

	private ForumJNDI jndi;
	public  ForumServiceImpl() {
		this.jndi = new ForumJNDI();
	}
	@Override
	public List<ForumVO> getAll() {
		return this.jndi.getAll();
	}

	@Override
	public ForumVO get(Integer forum_id) {
		return this.jndi.get(forum_id);
	}

	@Override
	public boolean add(ForumVO obj) {
		return this.jndi.add(obj);
	}

	@Override
	public boolean update(ForumVO obj) {
		return this.jndi.update(obj);
	}

	@Override
	public boolean delete(Integer id) {
		return this.jndi.delete(id);
	}
	@Override
	public boolean activation(Integer forumId, Integer status) {
		return this.jndi.activation(forumId, status);
	}
	@Override
	public boolean blockade(Integer forumId) {
		return this.jndi.blockade(forumId);
	}

}
