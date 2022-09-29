package com.store.service.impl;

import java.util.List;

import com.store.model.StoreDAO;
import com.store.model.StoreJNDI;
import com.store.model.StoreVO;
import com.store.service.StoreService;
import com.store_img.model.Store_imgVO;

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
	
	public List<StoreVO> findStorepageByStoreId(Integer store_id) {
		return storedao.findStorepageByStoreId(store_id);
	}
	
	@Override
	public StoreVO findStoreAccount(String account) {
		return storedao.findStoreAccount(account);
	}

	@Override
	public List<StoreVO> findStoreFrontpageBythemeId(Integer themeId) {

		return storedao.findStoreFrontpageBythemeId(themeId);
	}

	@Override
	public StoreVO updateStoreInformation(StoreVO vo) {
		String phone =  vo.getPhone();
		String email = vo.getEmail();
		String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
		String phoneRex = "\\d{10}";
		
		if ("".equals(phone.trim())) {
			vo.setMessage("電話請勿空白");
			vo.setSuccessful(false);
			return vo;
		}else if(!phone.trim().matches(phoneRex)) {
			vo.setMessage("電話請輸入正確");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(email)) {
			vo.setMessage("Email請勿空白");
			vo.setSuccessful(false);
			return vo;
		}else if(!email.trim().matches(emailRex)) {
			vo.setMessage("Email請符合格式");
			vo.setSuccessful(false);
			return vo;
		}
		
		storedao.updateStoreInformation(vo);
		vo.setSuccessful(true);
		return vo;
	}

	



}
