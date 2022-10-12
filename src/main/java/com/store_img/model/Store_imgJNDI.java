package com.store_img.model;

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
import com.product_img.model.Product_imgVO;

public class Store_imgJNDI implements Store_imgDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Store_imgVO insert(Store_imgVO img) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(img);
			transaction.commit();
			return img;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public void update(Store_imgVO img) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.createQuery("Update Store_imgVO set img = :img where storeId = :storeId and status1 = :status ")
					.setParameter("img", img.getImg()).setParameter("storeId", img.getStoreId())
					.setParameter("status", img.getStatus1()).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}

	// 取資料時用
	public Store_imgVO findImgByStoreIdandSratus(Integer store_id, Integer status) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Store_imgVO> query = session
					.createQuery("From Store_imgVO where store_id = :store_id and status1 = :status", Store_imgVO.class)
					.setParameter("store_id", store_id)
					.setParameter("status", status);
			Store_imgVO vo = query.uniqueResult();
			transaction.commit();
			return vo;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Store_imgVO> getbackInformation(String account) {
		String sql = "select s.name , s.dayoff , s.work_open , s.work_end , s.produce , i.img  from store s\n"
				+ "left join store_img i\n" + "on s.store_id = i.store_id\n" + "where s.account = ?";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store_imgVO img = new Store_imgVO();
				img.setName(rs.getString("name"));
				img.setDayoff(rs.getString("dayoff"));
				img.setWork_open(rs.getString("work_open"));
				img.setWork_end(rs.getString("work_end"));
				img.setProduce(rs.getString("produce"));
				// 為了第一次要取店家名字給前端
				if (rs.getBytes("img") != null) {
					img.setImg(new String(rs.getBytes("img")));
				}
				list.add(img);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id) {
		String sql = "SELECT img , status FROM store_img\n" + "where store_id = ?";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store_imgVO img = new Store_imgVO();
				img = new Store_imgVO();
				img.setImg(new String(rs.getBytes("img")));
				img.setStatus1(rs.getInt("status"));
				list.add(img);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
