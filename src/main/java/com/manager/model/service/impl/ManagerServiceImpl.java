package com.manager.model.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.manager.model.ManagerJNDIDAO;
import com.manager.model.ManagerVO;
import com.manager.model.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	private ManagerJNDIDAO dao ;

	public ManagerServiceImpl() {
		dao = new ManagerJNDIDAO();
	}
	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
	
	public boolean delete(Integer id) {
		 return dao.delete(id);
	}
	
	public ManagerVO get(Integer id) {
		return dao.get(id);
	}
	
	
	public boolean insert(String account,String password,LocalDate birthday) {
		ManagerVO vo=new ManagerVO();
		vo.setAccount(account);
		vo.setPassword(password);
		vo.setBirthday(birthday);
		return dao.add(vo);
	}
	
	public boolean update(Integer member_id, String password, Integer status) {
		ManagerVO vo = new ManagerVO();
		vo.setPassword(password);
		vo.setManager_id(member_id);
		vo.setStatus(status);
		return dao.update(vo);
	}
	public ManagerVO login(String account,String password) {
		return dao.login(account, password);
	}

	@Override
	public ManagerVO forgetPassword(String account, LocalDate birthday) {
		return dao.forgetPassword(account, birthday);
	}
	@Override
	public boolean update(ManagerVO obj) {
		return false;
	}
	@Override
	public void updateLoginTime(Integer id) {
		dao.updateLoginTime(id);
	}
	

}
