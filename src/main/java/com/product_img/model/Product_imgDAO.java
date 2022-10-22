package com.product_img.model;

import java.util.List;

import com.common.CoreDao;

public interface Product_imgDAO extends CoreDao{

	public void insert(Product_imgVO img);
	public void update(Product_imgVO img);
	public void delete(Integer img_id);
	public Product_imgVO findByProductID(Integer prd_id);
	public List<Product_imgVO>getAll();
	
	
}
