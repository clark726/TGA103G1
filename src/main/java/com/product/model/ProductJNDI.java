package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.common.HibernateUtil;

public class ProductJNDI implements ProductDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

		String sql = "select product_id , name , price, store_id ,description, type_id , stock , status, date \n"
				+ "from product\n" + "where product_id = ?;";
		ProductVO product = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, product_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductVO();
				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setStore_id(rs.getInt("store_id"));
				product.setDescription(rs.getString("description"));
				product.setType_id(rs.getInt("type_id"));
				product.setStock(rs.getInt("stock"));
				product.setStatus(rs.getInt("status"));
				product.setDate(rs.getDate("date"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
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
		String sql = "select p.product_id , p.name , price, store_id ,description, type_id , stock , status, p.date , img \n"
				+ "from product p\n" + "	left join product_img m\n" + "    on p.product_id = m.product_id;";
		List<ProductVO> list = new ArrayList<>();
		ProductVO product = null;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductVO();
				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setStore_id(rs.getInt("store_id"));
				product.setDescription(rs.getString("description"));
				product.setType_id(rs.getInt("type_id"));
				product.setStock(rs.getInt("stock"));
				product.setStatus(rs.getInt("status"));
				product.setDate(rs.getDate("date"));

//				List<Object> img = new ArrayList<Object>();
//				img.add(rs.getObject("img"));
				product.setImg(rs.getBytes("img"));

				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean updateStatus(Integer id, Integer status) {
		int row = 0;
		String sql = "UPDATE `product` SET `status` = ? WHERE (`product_id` = ?);";
		try (PreparedStatement ps = ds.getConnection().prepareStatement(sql);) {
			ps.setObject(1, status);
			ps.setObject(2, id);
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row == 1;
	}
}
