package com.product.model;

import java.util.List;

import com.order.model.OrderVO;


public interface ProductDAO {

	public Integer insert(ProductVO productvo);
	public Integer update(ProductVO productvo);
	public void delete(Integer product_id);
	public ProductVO findByPrimaryKey(Integer product_id);
	public List<ProductVO> getAll();
	public List<ProductVO> ShowStoreProduct(Integer store_id);
	public boolean updateStatus(Integer id,Integer status);
	public List<ProductVO> getProductBytypeId(Integer type_id);
	public void updateStock (Integer product_id , Integer amount);
}
