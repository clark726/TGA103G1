package com.store_img.service.impl;

import java.util.List;

import com.store_img.model.Store_imgDAO;
import com.store_img.model.Store_imgJNDI;
import com.store_img.model.Store_imgVO;
import com.store_img.service.Store_imgService;

public class Store_imgServiceImpl implements Store_imgService{

	
	private Store_imgDAO storeimgdao;

	public Store_imgServiceImpl() {
		
		storeimgdao = new Store_imgJNDI();
	
	}
	@Override
	public void insert(Store_imgVO img) {
		storeimgdao.insert(img);
	}

	@Override
	public void update(Store_imgVO img) {
		
	}

	@Override
	public void delete(Integer img_id) {
		
	}

	@Override
	public Store_imgVO findByPrimaryKey(Integer img_id) {
		return null;
	}

	@Override
	public List<Store_imgVO> getAll() {
		return null;
	}

}
