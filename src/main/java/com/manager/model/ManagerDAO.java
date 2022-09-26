package com.manager.model;

import java.util.List;

public interface ManagerDAO {
	List<ManagerVO> getAll();

    ManagerVO get(Integer manager_id);

    boolean add(ManagerVO obj);

    boolean update(ManagerVO obj);

    boolean delete(Integer id);
    
}
