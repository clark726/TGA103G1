package com.store.model;

import java.util.List;

public class StoreService {

	private StoreJNDIDAO store;
	
	public StoreService() {
		store = new StoreJNDIDAO();
	}
	
	public boolean addStore( String account, String name, String password, String phone, String email,
			String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end ,String produce) {
		
		StoreVO storevo = new StoreVO();
		
		storevo.setAccount(account);
		storevo.setName(name);
		storevo.setPassword(password);
		storevo.setPhone(phone);
		storevo.setAddress(address);
		storevo.setLng(lng);
		storevo.setLat(lat);
		storevo.setTheme_id(theme_id);
		storevo.setDayoff(dayoff);
		storevo.setWork_open(work_open);
		storevo.setWork_end(work_end);
		storevo.setProduce(produce);
		return store.insert(storevo);
	}
	//預留給 Struts 2 或 Spring MVC 用
	public boolean addStore(StoreVO storevo) {
		 return store.insert(storevo);
	}
	
	public boolean updateStore(Integer store_id ,String account, String name, String password, String phone, String email,
			String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end ,String produce) {
		
		StoreVO storevo = new StoreVO();
		storevo.setstore_id(store_id);
		storevo.setAccount(account);
		storevo.setName(name);
		storevo.setPassword(password);
		storevo.setPhone(phone);
		storevo.setEmail(email);
		storevo.setAddress(address);
		storevo.setLng(lng);
		storevo.setLat(lat);
		storevo.setTheme_id(theme_id);
		storevo.setDayoff(dayoff);
		storevo.setWork_open(work_open);
		storevo.setWork_end(work_end);
		storevo.setProduce(produce);
		
		return store.update(storevo);
	}
	

	
	public void deleteStore(Integer store_id) {
		store.delete(store_id);
	}
	
	public StoreVO findStoreVO(Integer store_id) {
		return store.findStoreVO(store_id);
	}
	
	public List<StoreVO> getAllStore(){
		return store.getAll();
	}
}
