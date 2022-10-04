package com.store.service;

import java.util.List;

import com.store.model.StoreVO;

public interface StoreService {

	public boolean addStore(StoreVO storevo);
	public StoreVO storeSumit(StoreVO storevo);
	//Spring MVC
	public void addStore2(StoreVO storevo);
	public StoreVO updateStore(StoreVO storeVO);
	public void deleteStore(Integer store_id);
	public StoreVO getOneStore(Integer store_id);
	public List<StoreVO> getAllStore();
	public StoreVO login(StoreVO vo);
	public StoreVO findStoreAccount(String account);
	public List<StoreVO> findStoreFrontpageBythemeId(Integer themeId);
	public List<StoreVO> findStorepageByStoreId(Integer store_id);
	public StoreVO updateStoreInformation(StoreVO storeVO);
}
