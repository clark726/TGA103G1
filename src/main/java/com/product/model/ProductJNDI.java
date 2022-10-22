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
		getSession().persist(productVO);
		return productVO.getProduct_id();

	}

	@Override
	public Integer update(ProductVO newProductVO) {
		ProductVO product = getSession().load(ProductVO.class, newProductVO.getProduct_id());

		product.setName(newProductVO.getName());
		product.setPrice(newProductVO.getPrice());
		product.setStore_id(newProductVO.getStore_id());
		product.setDescription(newProductVO.getDescription());
		product.setType_id(newProductVO.getType_id());
		product.setStock(newProductVO.getStock());
		product.setStatus(newProductVO.getStatus());
		return product.getProduct_id();

	}

	@Override
	public void delete(Integer product_id) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			ProductVO productVO = session.get(ProductVO.class, product_id);
			System.out.println(productVO.getProduct_id());
			session.delete(productVO);
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {

		Query<ProductVO> query3 = getSession()
				.createQuery("From ProductVO where product_id = :product_id", ProductVO.class)
				.setParameter("product_id", product_id);
		ProductVO vo = query3.uniqueResult();
		return vo;

	}

	@Override
	public List<ProductVO> ShowStoreProduct(Integer store_id) {
		Query<ProductVO> query3 = getSession().createQuery("From ProductVO where store_id = :store_id", ProductVO.class)
				.setParameter("store_id", store_id);
		List<ProductVO> list3 = query3.list();
		return list3;

	}

	@Override
	public List<ProductVO> getAll() {
		Query<ProductVO> query = getSession().createQuery("From ProductVO", ProductVO.class);
		List<ProductVO> productVO = query.list();
		return productVO;
	}

	@Override
	public boolean updateStatus(Integer id, Integer status) {
		int row = 0;
		row = getSession().createQuery("Update ProductVO set status = :status where product_id = :product_id")
				.setParameter("status", status).setParameter("product_id", id).executeUpdate();
		return row != 0;

	}

	@Override
	public List<ProductVO> getProductBytypeId(Integer type_id) {
		Query<ProductVO> query = getSession().createQuery("From ProductVO where type_id = :type_id", ProductVO.class)
				.setParameter("type_id", type_id);
		List<ProductVO> list = query.list();
		return list;

	}

	@Override
	public void updateStock(Integer product_id, Integer amount) {
		getSession().createQuery("Update ProductVO set stock = stock - :amount where product_id = :product_id")
				.setParameter("amount", amount).setParameter("product_id", product_id).executeUpdate();
	}
}
