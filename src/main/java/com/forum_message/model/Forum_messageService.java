package com.forum_message.model;

import java.time.LocalDateTime;
import java.util.List;


public class Forum_messageService {
	private Forum_messageJNDIDAO dao;

	public List<Integer> beforeDelete(Integer id){
		return dao.beforeDelete(id);
	}
	public Forum_messageService(){
		dao = new Forum_messageJNDIDAO();
	}

	public List<Forum_messageVO> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public Forum_messageVO get(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public Forum_messageVO insert(Integer member_id, Integer forum_id,String context) {
		Forum_messageVO result = new Forum_messageVO(member_id,forum_id,context);
		dao.insert(result);
		return result;
	}

	public Forum_messageVO update(Integer message_id, Integer member_id, Integer forum_id,String context,LocalDateTime date) {
		Forum_messageVO result = new Forum_messageVO(message_id, member_id, forum_id,context,date);
		dao.update(result);
		return result;
	}
}
