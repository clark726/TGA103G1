package com.product_img.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.common.HibernateUtil;

public class Product_imgJNDI implements Product_imgDAO {

	@Override
	public void insert(Product_imgVO img) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(img);
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product_imgVO product_imgVO) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.createQuery("Update Product_imgVO set img = :img where product_id = :product_id")
					.setParameter("img", product_imgVO.getImg())
					.setParameter("product_id", product_imgVO.getProduct_id()).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer img_id) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Product_imgVO productVO = session.load(Product_imgVO.class, img_id);
			session.remove(productVO);
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Product_imgVO findByProductID(Integer prd_id) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Product_imgVO> query = session
					.createQuery("From Product_imgVO where product_id = :product_id", Product_imgVO.class)
					.setParameter("product_id", prd_id);
			Product_imgVO vo = query.uniqueResult();
			transaction.commit();
			return vo;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Product_imgVO> getAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Product_imgVO> query = session.createQuery("From Product_imgVO ", Product_imgVO.class);
			List<Product_imgVO> list = query.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

}
