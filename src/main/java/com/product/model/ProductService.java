package com.product.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.List;

import com.product_img.model.Product_imgDAO;
import com.product_img.model.Product_imgJNDI;
import com.product_img.model.Product_imgVO;
import com.product_img.model.Product_imgVOJDBC;

public class ProductService {
	private ProductDAO productdao;
	private Product_imgDAO imgdao;

	public ProductService() {
		productdao = new ProductJNDI();
		imgdao = new Product_imgJNDI();
	}

	public ProductVO addProduct(ProductVO productVO, List<Product_imgVO> imgList) {

//		ProductVO vo = new ProductVO();
//		vo.setName(name);
//		vo.setPrice(price);
//		vo.setStore_id(store_id);
//		vo.setDescription(description);
//		vo.setType_id(type_id);
//		vo.setStock(stock);

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

	public ProductVO updatStore(Integer product_id, String name, Integer price, Integer store_id, String description,
			Integer type_id, Integer stock, Integer status) {

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

	public List<ProductVO> getAll() {
		List<ProductVO> list = productdao.getAll();
		for (ProductVO vo : list) {
			String base64Str = Base64.getEncoder().encodeToString(toByteArray(vo.getImg()));
			vo.setImg((String)base64Str);
		}

		return productdao.getAll();
	}

	public ProductVO getOneProduct(Integer product_id) {
		return productdao.findByPrimaryKey(product_id);
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
