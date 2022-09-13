package com.manager.model;
import java.util.List;

public class ManagerService {
	private ManagerJNDIDAO dao ;

	public ManagerService() {
		dao = new ManagerJNDIDAO();
	}
	
	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
	
	public void delete(Integer id) {
		dao.delete(id);
	}
	
	public ManagerVO get(Integer id) {
		return dao.get(id);
	}
	
	
	public ManagerVO insert(String account,String password) {
		ManagerVO result = new ManagerVO(account,password);
		dao.add(result);
		return result;
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
