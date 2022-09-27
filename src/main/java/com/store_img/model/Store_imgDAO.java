package com.store_img.model;

import java.util.List;

public interface Store_imgDAO {
	
	public Store_imgVO insert(Store_imgVO img);
	public void update(Store_imgVO img);
	public void delete(Integer img_id);
	public Store_imgVO findByPrimaryKey(Integer img_id);
	public List<Store_imgVO> getAll();
	public Store_imgVO findImgByStoreIdandSratus(Integer store_id , Integer Status);
	public List<Store_imgVO> getbackInformation(String account);
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id);
	
}
