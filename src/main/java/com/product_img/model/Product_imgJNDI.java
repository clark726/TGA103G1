package com.product_img.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_imgJNDI implements Product_imgDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(Product_imgVO img) {

		String sql = "insert into product_img (img , product_id)\n"
				+ "values(?,?);";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql) ){
			
			
			ps.setBytes(1,img.getImg());
			ps.setInt(2, img.getProduct_id());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product_imgVO img) {

		String sql = "update product_img set   img = ? where product_id = ?\n";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
		
			ps.setBytes(1, img.getImg());
			ps.setInt(2, img.getProduct_id());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer img_id) {

		String sql = "delete from product_img where img_id = ?;";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, img_id);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product_imgVO findByProductID(Integer prd_id) {

		String sql = "SELECT img FROM product_img where product_id = ?";
		Product_imgVO img = null;

		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setInt(1, prd_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				img = new Product_imgVO();
				// 取照片
				img.setImg(rs.getBytes("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return img;
	}

	@Override
	public List<Product_imgVO> getAll() {
		String sql = "SELECT img_id , date , img , product_id FROM  product_img";
		List<Product_imgVO> list = new ArrayList<Product_imgVO>();
		Product_imgVO img = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				img = new Product_imgVO();
				img.setImg_id(rs.getInt("img_id"));
				img.setDate(rs.getDate("date"));
				img.setImg(rs.getBytes("imgs"));
				img.setProduct_id(rs.getInt("product_id"));
				
				list.add(img);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	public static void main(String[] args) throws IOException {
		Product_imgVOJDBC jdbc = new Product_imgVOJDBC();
		Product_imgVO img = new Product_imgVO();
//		FileInputStream in = new FileInputStream("/Users/chenguanlun/Desktop/圖檔/4.jpeg");
//		byte[] b = new byte[(int)in.available()];
//		try {
//			
//			in.read(b);
//			img.setImg(b);
//			img.setProduct_id(1);
//			jdbc.insert(img);
//			
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		System.out.println(jdbc.findByPrimaryKey(3));
//		List<Product_imgVO> list = new ArrayList<>();
//		list = jdbc.getAll();
//		for(Product_imgVO all2 : list) {
//			System.out.println(all2);
//		}
	}
}
