package com.product.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.List;

import com.product.model.ProductDAO;
import com.product.model.ProductJNDI;
import com.product.model.ProductVO;
import com.product.service.ProductService;
import com.product_img.model.Product_imgDAO;
import com.product_img.model.Product_imgJNDI;
import com.product_img.model.Product_imgVO;
import com.product_img.model.Product_imgVOJDBC;
import com.store.model.StoreDAO;
import com.store.model.StoreJNDI;
import com.store.model.StoreVO;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productdao;
	private Product_imgDAO imgdao;
	private StoreDAO storedao;

	public ProductServiceImpl() {
		productdao = new ProductJNDI();
		imgdao = new Product_imgJNDI();
		storedao = new StoreJNDI();
	}

	/**
	 * @param productVO
	 * @param imgList
	 * @return
	 */
	public ProductVO addProduct(ProductVO productVO, List<Product_imgVO> imgList) {

		Integer product_id = productdao.insert(productVO);
		for (Product_imgVO imgVO : imgList) {
			imgVO.setProduct_id(product_id);
			imgdao.insert(imgVO);
		}
		return productVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addProduct(ProductVO productvo) {
		productdao.insert(productvo);
	}

	public ProductVO updatProduct(ProductVO productvo ,List<Product_imgVO> imgList ) {

//		ProductVO vo = new ProductVO();
//		vo.setProduct_id(product_id);
//		vo.setName(name);
//		vo.setPrice(price);
//		vo.setStore_id(store_id);
//		vo.setDescription(description);
//		vo.setType_id(type_id);
//		vo.setStock(stock);
//		vo.setStatus(status);
	
		Integer product_id = productdao.update(productvo);
		for (Product_imgVO imgVO : imgList) {
			imgVO.setProduct_id(product_id);
			imgdao.update(imgVO);
		}
		
		return productvo;
	}

	public void delete(Integer product_id) {
		productdao.delete(product_id);
	}

	public List<ProductVO> getAll() {
		List<ProductVO> list = productdao.getAll();
		for (ProductVO vo : list) {
			String base64Str = Base64.getEncoder().encodeToString(toByteArray(vo.getImg()));
			vo.setImg((String)base64Str);
		}

		return list;
	}

	public ProductVO getOneProduct(Integer product_id) {
		return productdao.findByPrimaryKey(product_id);
	}
	
	@Override
	public List<ProductVO> ShowStoreProduct(String account) {
		StoreVO store = storedao.findStoreAccount(account);
		List<ProductVO> list = productdao.ShowStoreProduct(store.getStore_id());
		return list;
	}

	
	// object 轉 byte[]
	public byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
