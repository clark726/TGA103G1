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
		

		Store_imgVO imgmain = new Store_imgVO();
		imgmain.setImg(img.getImg());
		imgmain.setStore_id(img.getStore_id());
		imgmain.setStatus1(1);
		storeimgdao.insert(imgmain);
		
		Store_imgVO imgfirst = new Store_imgVO();
		imgfirst.setImg(img.getFirstImg());
		imgfirst.setStore_id(img.getStore_id());
		imgfirst.setStatus1(2);
		storeimgdao.insert(imgfirst);
		
		Store_imgVO imgsecond = new Store_imgVO();
		imgsecond.setImg(img.getSecondImg());
		imgsecond.setStore_id(img.getStore_id());
		imgsecond.setStatus1(3);
		storeimgdao.insert(imgsecond);
		
		Store_imgVO meanu1 = new Store_imgVO();
		meanu1.setImg(img.getMeanuImg());
		meanu1.setStore_id(img.getStore_id());
		meanu1.setStatus1(4);
		storeimgdao.insert(meanu1);
		
		Store_imgVO meanu2 = new Store_imgVO();
		meanu2.setImg(img.getMeanuImg2());
		meanu2.setStore_id(img.getStore_id());
		meanu2.setStatus1(5);
		storeimgdao.insert(meanu2);
		
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
