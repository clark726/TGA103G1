package com.common;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.product.model.ProductVO;

public class TestApp {

	public static void main(String[] args) {
		
		ProductVO productVO = new ProductVO();
		productVO.setProduct_id(134);
		productVO.setName("ddd");
		productVO.setPrice(11);
		productVO.setStore_id(1);
		productVO.setDescription("ddd");
		productVO.setType_id(1);
		productVO.setStock(11);
		productVO.setStatus(1);
		
		System.out.println( select(1));
	}
	
	public static List<ProductVO>  select(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
		Transaction transaction = session.beginTransaction();
		Query<ProductVO> query3 = session.createQuery("From ProductVO where store_id = :store_id", ProductVO.class)
				.setParameter("store_id",id);
		List<ProductVO> list3 = query3.list();
		transaction.commit();
		return list3;
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
}
