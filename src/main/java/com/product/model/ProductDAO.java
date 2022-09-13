package com.product.model;

import java.util.List;


public interface ProductDAO {

	public void insert(ProductVO productvo);
	public void update(ProductVO productvo);
	public void delete(Integer product_id);
	public ProductVO findByPrimaryKey(Integer product_id);
	public List<ProductVO> getAll();
}
