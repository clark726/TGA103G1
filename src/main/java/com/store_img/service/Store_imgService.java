package com.store_img.service;

import java.util.List;

import com.store_img.model.Store_imgVO;

public interface Store_imgService {

	public Store_imgVO insert(Store_imgVO img);
	public void update(Store_imgVO img);
	public void delete(Integer img_id);
	public Store_imgVO findByPrimaryKey(Integer img_id);
	public List<Store_imgVO> getAll();
	public List<Store_imgVO> getbackInformation(String account);
}
