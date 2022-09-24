package com.manager.model.service;

import java.util.List;

import com.manager.model.ManagerVO;

public interface ManagerService {
	public List<ManagerVO> getAll();
	public boolean delete(Integer id);
	public ManagerVO get(Integer id);
	public boolean insert(String account,String password);
	public ManagerVO update(Integer member_id, String account, String password);
	public boolean check(String account,String password);
}
