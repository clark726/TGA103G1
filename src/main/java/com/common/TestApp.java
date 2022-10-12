package com.common;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.product.model.ProductVO;
import com.product_img.model.Product_imgVO;

public class TestApp {

	public static void main(String[] args) {

//		System.out.println(select(125).getProduct_imgVO().get(0).getImg().length);
		List<ProductVO> productVO = selectAll();
		for(ProductVO vo : productVO) {
			System.out.println(vo.getProduct_imgVO().get(0).getImg().length);
		}
	}
	public static void update(Integer id, Integer status) {
		String sql = "UPDATE `product` SET `status` = ? WHERE (`product_id` = ?);";
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.createQuery("Update ProductVO set status = :status where product_id = :product_id")
					.setParameter("status", status)
					.setParameter("product_id",id)
					.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	
	public static List<ProductVO> selectAll(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<ProductVO>  query = session.createQuery("From ProductVO",ProductVO.class);
			List<ProductVO> productVO  = query.list();
			transaction.commit();
			
			return productVO;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public static ProductVO select(Integer id) {
		String sql = "select p.product_id , p.name , price, store_id ,description, type_id , stock , status, p.date , img \n"
				+ "from product p\n" + "	left join product_img m\n" + "    on p.product_id = m.product_id;";
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			ProductVO productVO = session.get(ProductVO.class, id);
			transaction.commit();
			return productVO;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
}
