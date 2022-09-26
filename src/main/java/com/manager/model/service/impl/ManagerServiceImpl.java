package com.manager.model.service.impl;

import java.util.List;

import com.manager.model.ManagerJNDIDAO;
import com.manager.model.ManagerVO;
import com.manager.model.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	private ManagerJNDIDAO dao ;

	public ManagerServiceImpl() {
		dao = new ManagerJNDIDAO();
	}

//	public List<Integer> getMessageReportByMessageId(Integer messageId){
//		return dao.getMessageReportByMessageId(messageId);
//	}
	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
	
	public boolean delete(Integer id) {
		 return dao.delete(id);
	}
	
	public ManagerVO get(Integer id) {
		return dao.get(id);
	}
	
	
	public boolean insert(String account,String password) {
		return dao.add(new ManagerVO(account,password));
	}
	
	public ManagerVO update(Integer member_id, String account, String password) {
		ManagerVO result = new ManagerVO(member_id,account,password);
		dao.update(result);
		return result;
	}
	public boolean check(String account,String password) {
		return dao.check(account, password);
	}
}
