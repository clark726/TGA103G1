package com.store.service.impl;

import java.util.List;

import com.store.model.StoreDAO;
import com.store.model.StoreJNDI;
import com.store.model.StoreVO;
import com.store.service.StoreService;

public class StoreServiceImpl implements StoreService {

	private StoreDAO storedao;

	public StoreServiceImpl() {

		storedao = new StoreJNDI();
	}

	public boolean addStore(StoreVO storevo) {

		if ((storedao.findStoreAccount(storevo.getAccount()) == null)) {
			storedao.insert(storevo);
			return true;
		}
		return false;

	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addStore2(StoreVO storevo) {
		storedao.insert(storevo);
	}

	public StoreVO updateStore(StoreVO store) {

		String dayoff = store.getDayoff();
		String produce = store.getProduce();
		String work_open = store.getWork_open();
		String work_end = store.getWork_end();

		if ("".equals(dayoff)) {
			store.setMessage("公休請勿空白");
			store.setSuccessful(false);
			return store;
		}
		if ("".equals(produce)) {
			store.setMessage("介紹請勿空白");
			store.setSuccessful(false);
			return store;
		}
		String timeRex = "\\d{2}\\:\\d{2}";
		if ("".equals(work_open)) {
			store.setMessage("營業開始請勿空白");
			store.setSuccessful(false);
			return store;
		} else if (!(work_open.trim().matches(timeRex))) {
			store.setMessage("營業開始請符合格式");
			return store;
		}
		if ("".equals(work_end)) {
			store.setMessage("營業結束請勿空白");
			store.setSuccessful(false);
			return store;
		} else if (!(work_end.trim().matches(timeRex))) {
			store.setMessage("營業結束請符合格式");
			return store;
		}

		storedao.updateProduce(store);
		store.setSuccessful(true);
		return store;
	}

	public void deleteStore(Integer store_id) {
		storedao.delete(store_id);
	}

	public StoreVO getOneStore(Integer store_id) {
		return storedao.findByPrimaryKey(store_id);
	}

	public List<StoreVO> getAllStore() {
		return storedao.getAll();
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
		if (storedao.Login(account, password) == null) {
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

		return storedao.findStoreAccount(account);
	}

}
