package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Order_detailJDBC implements Order_detailDAO{

	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/barjarjo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	

	@Override
	public boolean update(Order_detailVO obj) {
		int rowCount = 0;
		String sql = "Update order_detail set amount = ? where order_id = ? and product_id = ? ;";
		try(Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, obj.getAmount());
			ps.setInt(2, obj.getOrder_id());
			ps.setInt(3, obj.getProduct_id());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount == 1;
		
	}

	@Override
	public boolean delete(Integer order_id,Integer product_id) {
		int rowCount = 0;
		String sql = "Delete from order_detail where order_id = ? and product_id = ?;";
		try(Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1,order_id);
			ps.setInt(2,product_id);
			
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public Order_detailVO findByPrimaryKey(Integer id) {
		Order_detailVO od = null;
		
		String sql = "select order_id, product_id, amount "
				+ " from order_detail where order_id = ? ;";
		try(Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer order_id = rs.getInt(1);
				Integer product_id = rs.getInt(2);
				Integer amount = rs.getInt(3);
				od = new Order_detailVO(order_id,product_id,amount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return od;
	}

	@Override
	public List<Order_detailVO> getAllByOrderId(Integer store_id) {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		String sql = "select order_id, product_id, amount "
				+ " from order_detail;";
		try(Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Integer order_id = rs.getInt(1);
					Integer product_id = rs.getInt(2);
					Integer amount = rs.getInt(3);
					
					Order_detailVO od = new Order_detailVO(order_id ,product_id ,amount);
					list.add(od);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		Order_detailDAO dao = new Order_detailJDBC();
		
		// ??????????????????
//		List<Order_detailVO> list = dao.getAll();
//		System.out.println("---------Total"+ list.size() +" ???");
//		for(Order_detailVO odvo : list) {
//			System.out.println(odvo.toString());
//			System.out.println();
//		}
		//??????????????????
		System.out.println("---------------------");
		Order_detailVO od = dao.findByPrimaryKey(1);
		System.out.println(od);
		System.out.println("---------------------");
		
		//????????????
//		Order_detailVO odInsert = new Order_detailVO(2,1,2);
//		System.out.println(dao.insert(odInsert) ? "Insert Success" : "Insert Failed");
//		System.out.println("---------------------");
		
		
		//????????????
//		Order_detailVO upod = new Order_detailVO(3,1,4);
//		System.out.println(dao.update(upod) ? "Update Success" : "Update Failed" );
//		System.out.println("---------------------");
		
		
		
		//????????????
//		System.out.println(dao.delete(3, 2) ? "Delete Success" : "Delete Failed");
//		System.out.println("---------------------");
	}

	@Override
	public void updateStatus(Integer order_id, Integer status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order_detailVO> getAllBymember(Integer member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getOneOrderDetail(Integer order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Integer order_id, Integer product_id, Integer amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
