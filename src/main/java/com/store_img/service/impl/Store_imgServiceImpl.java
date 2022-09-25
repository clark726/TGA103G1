package com.store_img.service.impl;

import java.util.List;

import com.store_img.model.Store_imgDAO;
import com.store_img.model.Store_imgJNDI;
import com.store_img.model.Store_imgVO;
import com.store_img.service.Store_imgService;

public class Store_imgServiceImpl implements Store_imgService {

	private Store_imgDAO storeimgdao;

	public Store_imgServiceImpl() {

		storeimgdao = new Store_imgJNDI();

	}

	@Override
	public Store_imgVO insert(Store_imgVO img) {

		Store_imgVO imgmain = new Store_imgVO();
		imgmain.setImg(img.getImg());
		imgmain.setStore_id(img.getStore_id());
		imgmain.setStatus1(1);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 1) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("不得為空值");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 1) != null && img.getImg() != null) {
			storeimgdao.update(imgmain);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 1) == null && img.getImg() != null) {
			storeimgdao.insert(imgmain);

		}

		Store_imgVO imgfirst = new Store_imgVO();
		imgfirst.setImg(img.getFirstImg());
		imgfirst.setStore_id(img.getStore_id());
		imgfirst.setStatus1(2);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 2) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("不得為空值");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 2) != null && img.getImg() != null) {
			storeimgdao.update(imgfirst);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 2) == null && img.getImg() != null) {
			storeimgdao.insert(imgfirst);

		}

		Store_imgVO imgsecond = new Store_imgVO();
		imgsecond.setImg(img.getSecondImg());
		imgsecond.setStore_id(img.getStore_id());
		imgsecond.setStatus1(3);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 3) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("不得為空值");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 3) != null && img.getImg() != null) {
			storeimgdao.update(imgsecond);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 3) == null && img.getImg() != null) {
			storeimgdao.insert(imgsecond);

		}

		Store_imgVO meanu1 = new Store_imgVO();
		meanu1.setImg(img.getMeanuImg());
		meanu1.setStore_id(img.getStore_id());
		meanu1.setStatus1(4);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 4) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("不得為空值");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 4) != null && img.getImg() != null) {
			storeimgdao.update(meanu1);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 4) == null && img.getImg() != null) {
			storeimgdao.insert(meanu1);

		}

		Store_imgVO meanu2 = new Store_imgVO();
		meanu2.setImg(img.getMeanuImg2());
		meanu2.setStore_id(img.getStore_id());
		meanu2.setStatus1(5);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 5) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("不得為空值");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 5) != null && img.getImg() != null) {
			storeimgdao.update(meanu2);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStore_id(), 5) == null && img.getImg() != null) {
			storeimgdao.insert(meanu2);

		}

		img.setSuccessful(true);
		return img;

	}
	
	@Override
	public List<Store_imgVO> getbackInformation(String account) {
		return storeimgdao.getbackInformation(account);
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
