package com.product.service;

import java.util.List;

import com.product.model.ProductVO;
import com.product_img.model.Product_imgVO;

public interface ProductService {

	public ProductVO addProduct(ProductVO productVO, List<Product_imgVO> imgList);
	//Spring MVC
	public void addProduct(ProductVO productvo);
	public ProductVO updatProduct(ProductVO productvo ,List<Product_imgVO> imgList );
	public void delete(Integer product_id);
	public List<ProductVO> getAll();
	public ProductVO getOneProduct(Integer product_id);
	public List<ProductVO> ShowStoreProduct(String account);
	public boolean updateStatus(Integer id,Integer status);
}
