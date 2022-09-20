package com.store.service.impl;

import java.util.List;

import com.store.model.StoreDAO;
import com.store.model.StoreJNDI;
import com.store.model.StoreVO;
import com.store.service.StoreService;

public class StoreServiceImpl implements StoreService {

	private StoreDAO store;

	public StoreServiceImpl() {

		store = new StoreJNDI();
	}

	public boolean addStore(StoreVO storevo) {

		if ((store.findStoreAccount(storevo.getAccount()) == null)) {
			System.out.println(store.findStoreAccount(storevo.getAccount()));
			store.insert(storevo);
			return true;
		}
		return false;

	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addStore2(StoreVO storevo) {
		store.insert(storevo);
	}

	public StoreVO updateStore(Integer store_id, String account, String name, String password, String phone,
			String email, String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end, String produce) {

		StoreVO storevo = new StoreVO();
		storevo.setStore_id(store_id);
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

	public List<StoreVO> getAllStore() {
		return store.getAll();
	}

	public StoreVO login(StoreVO vo) {

		String account = vo.getAccount();
		String password = vo.getPassword();

		if ("".equals(account)) {
			vo.setMessage("帳號未輸入");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(password)) {
			vo.setMessage("密碼未輸入");
			vo.setSuccessful(false);
			return vo;
		}
		if (store.Login(account, password) == null) {
			vo.setSuccessful(false);
			vo.setMessage("帳號或密碼錯誤！");
			return vo;
		} else {
			vo.setSuccessful(true);
		}

		return vo;
	}

	@Override
	public StoreVO findStoreId(String account) {

		return store.findStoreAccount(account);
	}

}
