package com.store.service;

import java.util.List;

import com.store.model.StoreVO;

public interface StoreService {

	public boolean addStore(StoreVO storevo);
	//Spring MVC
	public void addStore2(StoreVO storevo);
	public StoreVO updateStore(Integer store_id, String account, String name, String password, String phone,
			String email, String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end, String produce);
	public void deleteStore(Integer store_id);
	public StoreVO getOneStore(Integer store_id);
	public List<StoreVO> getAllStore();
	public StoreVO login(StoreVO vo);
	public StoreVO findStoreId(String account);
}
