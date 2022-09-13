package com.product.model;

import java.sql.Date;
import java.util.List;

import com.store.model.StoreVO;

public class ProductService {

	private ProductDAO productdao;
	
	public ProductService() {
		productdao = new ProductVOJDBC();
	}
	
	public ProductVO addProduct(String name, Integer price, Integer store_id, String description,
			Integer type_id, Integer stock) {
		
		ProductVO vo = new ProductVO();
		vo.setName(name);
		vo.setPrice(price);
		vo.setStore_id(store_id);
		vo.setDescription(description);
		vo.setType_id(type_id);
		vo.setStock(stock);
		productdao.insert(vo);
		
		return vo; 
	}
	
	public void insertimg() {
		
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addProduct(ProductVO productvo) {
		productdao.insert(productvo);
	}
	
	public ProductVO updatStore(Integer product_id ,String name, Integer price, Integer store_id, String description,
			Integer type_id, Integer stock , Integer status) {
		
		ProductVO vo = new ProductVO();
		vo.setProduct_id(product_id);
		vo.setName(name);
		vo.setPrice(price);
		vo.setStore_id(store_id);
		vo.setDescription(description);
		vo.setType_id(type_id);
		vo.setStock(stock);
		vo.setStatus(status);
		productdao.update(vo);
		
		return vo; 
	}
	
	public void delete(Integer product_id) {
		productdao.delete(product_id);
	}
	
	public List<ProductVO> getAll(){
		return productdao.getAll();
	}
	
	public ProductVO getOneProduct(Integer product_id) {
		return productdao.findByPrimaryKey(product_id);
	}

	
}
