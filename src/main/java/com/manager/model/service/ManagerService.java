package com.manager.model.service;

import java.time.LocalDate;
import java.util.List;

import com.manager.model.ManagerVO;

public interface ManagerService {
	List<ManagerVO> getAll();

    ManagerVO get(Integer manager_id);

    boolean insert(String account,String password,LocalDate birthday);

    boolean update(ManagerVO obj);

    boolean delete(Integer id);
    
    ManagerVO login(String account,String password);
    
    ManagerVO forgetPassword(String account,LocalDate birthday);
    
    void updateLoginTime(Integer id);
}
