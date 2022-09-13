package com.store.model;

import java.util.List;

public class StoreService {

	private StoreDAO store;
	
	public StoreService() {
		
		store = new StoreVOJDBC();
	}
	
	public StoreVO addStore( String account, String name, String password, String phone, String email,
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
		store.insert(storevo);
		
		
		return storevo;
		
	}
	//預留給 Struts 2 或 Spring MVC 用
	public void addStore(StoreVO storevo) {
		store.insert(storevo);
	}
	
	public StoreVO updateStore(Integer store_id ,String account, String name, String password, String phone, String email,
			String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end ,String produce) {
		
		StoreVO storevo = new StoreVO();
		storevo.setstore_id(store_id);
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
		
		store.update(storevo);
		
		return store.findByPrimaryKey(store_id);
	}
	

	
	public void deleteStore(Integer store_id) {
		store.delete(store_id);
	}
	
	public StoreVO getOneStore(Integer store_id) {
		return store.findByPrimaryKey(store_id);
	}
	
	public List<StoreVO> getAllStore(){
		return store.getAll();
	}
}
