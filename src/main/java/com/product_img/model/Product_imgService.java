package com.product_img.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.product.model.ProductVO;

public class Product_imgService {

	private Product_imgDAO productimgdao;

	public Product_imgService() {
		
		productimgdao = new Product_imgVOJDBC();
	
	}

	public Product_imgVO addimg(Integer product_id , byte[] img) {
		
		Product_imgVO imgvo = new Product_imgVO();
		imgvo.setProduct_id(product_id);
		imgvo.setImg(img);
		
		productimgdao.insert(imgvo);
		
		return imgvo;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
	public void addimg(Product_imgVO imgvo) {
		productimgdao.insert(imgvo);
	}
	
	public void delete(Integer img_id) {
		productimgdao.delete(img_id);
	}
	
	public List<Product_imgVO> getAll() {
		List<Product_imgVO> list = productimgdao.getAll();
			for(Product_imgVO list2 : list) {
				String base64Str = Base64.getEncoder().encodeToString(list2.getImg());
				Product_imgVO imgvo = new Product_imgVO();
				imgvo.setImgobj(base64Str);
				list.add(imgvo);
			}
		return list;
	}
	

	
	
}
