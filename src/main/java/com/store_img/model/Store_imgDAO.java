package com.store_img.model;

import java.util.List;

import com.common.CoreDao;
import com.store.model.StoreVO;

public interface Store_imgDAO extends CoreDao {
	
	public Store_imgVO insert(Store_imgVO img);
	public void update(Store_imgVO img);
	public Store_imgVO findImgByStoreIdandSratus(Integer store_id , Integer Status);
	public List<Store_imgVO> getbackInformation(String account);
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id);
	public List<StoreVO> getStoreImg();
	public List<StoreVO> getStoreImgByTheme(Integer theme_id);
}
