package com.forum_message.model;

import java.util.List;


public interface Forum_messageDAO {
	public void insert(Forum_messageVO obj);

	public void update(Forum_messageVO obj);

	public void delete(Integer id);

	public Forum_messageVO findByPrimaryKey(Integer id);

	public List<Forum_messageVO> getAll();
	
	public List<Integer> beforeDelete(Integer messageId);
}
