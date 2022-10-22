package com.product_img.model;

import java.util.List;

import org.hibernate.query.Query;

public class Product_imgJNDI implements Product_imgDAO {

	@Override
	public void insert(Product_imgVO img) {

		getSession().persist(img);
	}

	@Override
	public void update(Product_imgVO product_imgVO) {

		getSession().createQuery("Update Product_imgVO set img = :img where product_id = :product_id")
				.setParameter("img", product_imgVO.getImg()).setParameter("product_id", product_imgVO.getProduct_id())
				.executeUpdate();
	}

	@Override
	public void delete(Integer img_id) {

		Product_imgVO productVO = getSession().load(Product_imgVO.class, img_id);
		getSession().remove(productVO);
	}

	@Override
	public Product_imgVO findByProductID(Integer prd_id) {

		Query<Product_imgVO> query = getSession()
				.createQuery("From Product_imgVO where product_id = :product_id", Product_imgVO.class)
				.setParameter("product_id", prd_id);
		Product_imgVO vo = query.uniqueResult();
		return vo;

	}

	@Override
	public List<Product_imgVO> getAll() {
		Query<Product_imgVO> query = getSession().createQuery("From Product_imgVO ", Product_imgVO.class);
		List<Product_imgVO> list = query.list();
		return list;
	}
}
