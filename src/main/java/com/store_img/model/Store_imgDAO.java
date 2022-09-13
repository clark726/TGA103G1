package com.store_img.model;

import java.util.List;

public interface Store_imgDAO {
	
	public void insert(Store_imgVO img);
	public void update(Store_imgVO img);
	public void delete(Integer img_id);
	public Store_imgVO findByPrimaryKey(Integer img_id);
	public List<Store_imgVO> getAll();
}
