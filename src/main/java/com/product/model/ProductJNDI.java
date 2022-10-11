package com.product.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.common.HibernateUtil;

public class ProductJNDI implements ProductDAO {


	@Override
	public Integer insert(ProductVO productVO) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(productVO);
			transaction.commit();
			return productVO.getProduct_id();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer update(ProductVO newProductVO) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			ProductVO product = session.load(ProductVO.class, newProductVO.getProduct_id());

			product.setName(newProductVO.getName());
			product.setPrice(newProductVO.getPrice());
			product.setStore_id(newProductVO.getStore_id());
			product.setDescription(newProductVO.getDescription());
			product.setType_id(newProductVO.getType_id());
			product.setStock(newProductVO.getStock());
			product.setStatus(newProductVO.getStatus());
			transaction.commit();
			return product.getProduct_id();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void delete(Integer product_id) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			ProductVO productVO = session.load(ProductVO.class, product_id);
			session.remove(productVO);
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<ProductVO> query3 = session
					.createQuery("From ProductVO where product_id = :product_id", ProductVO.class)
					.setParameter("product_id", product_id);
			ProductVO vo = query3.uniqueResult();
			transaction.commit();
			return vo;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductVO> ShowStoreProduct(Integer store_id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<ProductVO> query3 = session.createQuery("From ProductVO where store_id = :store_id", ProductVO.class)
					.setParameter("store_id", store_id);
			List<ProductVO> list3 = query3.list();
			transaction.commit();
			return list3;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductVO> getAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<ProductVO> query = session.createQuery("From ProductVO", ProductVO.class);
			List<ProductVO> productVO = query.list();
			transaction.commit();

			return productVO;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateStatus(Integer id, Integer status) {
		int row = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
		 row = session.createQuery("Update ProductVO set status = :status where product_id = :product_id")
					.setParameter("status", status)
					.setParameter("product_id",id)
					.executeUpdate();
			transaction.commit();
			return row != 0;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public List<ProductVO> getProductBytypeId(Integer type_id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<ProductVO> query = session.createQuery("From ProductVO where type_id = :type_id", ProductVO.class)
					.setParameter("type_id", type_id);
			List<ProductVO> list = query.list();
			transaction.commit();
			return list;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
}
