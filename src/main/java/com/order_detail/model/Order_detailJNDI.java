package com.order_detail.model;

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

public class Order_detailJNDI implements Order_detailDAO {

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
	public boolean insert(Order_detailVO obj) {
		int rowCount = 0;
		String sql = "insert into order_detail(order_id, product_id, amount) values(? ,? ,?);";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getOrder_id());
			ps.setInt(2, obj.getProduct_id());
			ps.setInt(3, obj.getAmount());

			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public boolean update(Order_detailVO obj) {
		int rowCount = 0;
		String sql = "Update order_detail set amount = ? where order_id = ? and product_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getAmount());
			ps.setInt(2, obj.getOrder_id());
			ps.setInt(3, obj.getProduct_id());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public boolean delete(Integer order_id, Integer product_id) {
		int rowCount = 0;
		String sql = "Delete from order_detail where order_id = ? and product_id = ?;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, order_id);
			ps.setInt(2, product_id);

			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public Order_detailVO findByPrimaryKey(Integer id) {
		Order_detailVO od = null;

		String sql = "select order_id, product_id, amount " + " from order_detail where order_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer order_id = rs.getInt(1);
				Integer product_id = rs.getInt(2);
				Integer amount = rs.getInt(3);
				od = new Order_detailVO(order_id, product_id, amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return od;
	}

	@Override
	public List<Order_detailVO> getAllByOrderId(Integer order_id) {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();

		String sql = "select d.order_id , d.product_id , p.name product_name , d.amount , d.status  from product p\n"
				+ "	join(SELECT o.order_id , o.status , d.amount , d.product_id FROM `order` o\n"
				+ "				join order_detail d\n" + "				on o.order_id = d.order_id) d\n"
				+ "	on p.product_id = d.product_id\n" + "where d.order_id = ?;";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, order_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Order_detailVO vo = new Order_detailVO();
				vo.setOrder_id(rs.getInt("order_id"));
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setAmount(rs.getInt("amount"));
				vo.setStatus(rs.getInt("status"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void updateStatus(Integer order_id, Integer status) {
		String sql = "UPDATE `order` SET `status` = ? WHERE (`order_id` = ?);";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, status);
			ps.setInt(2, order_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
