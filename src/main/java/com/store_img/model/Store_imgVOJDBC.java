package com.store_img.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Store_imgVOJDBC implements Store_imgDAO{

	
	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	@Override
	public void insert(Store_imgVO img) {
		String sql = "insert into store_img (store_id , img)\n"
				+ "values(?, ?);";
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, img.getStore_id());
			ps.setBytes(2, img.getImg());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Store_imgVO img) {
		String sql = "update store_img set store_id = ? , img = ?\n"
				+ "where img_id = ?";
		try(Connection connection = DriverManager.getConnection(url, userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, img.getStore_id());
			ps.setBytes(2, img.getImg());
			ps.setInt(3, img.getImg_id());
			ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer img_id) {
		String sql = "delete from store_img where img_id = ?;";
		
		try(Connection connection = DriverManager.getConnection(url , userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, img_id);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Store_imgVO findByPrimaryKey(Integer img_id) {
		String sql = "select img_id , store_id , date , img from store_img\n"
				+ "where img_id = ?;";
		Store_imgVO img = null;
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, img_id);
			
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					img = new Store_imgVO();
					img.setImg_id(rs.getInt("img_id"));
					img.setStore_id(rs.getInt("store_id"));
					img.setDate(rs.getDate("date"));
					img.setImg(rs.getBytes("img"));
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return img;
	}

	@Override
	public List<Store_imgVO> getAll() {
		String sql = "select img_id , store_id , date , img from store_img";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();
		Store_imgVO img = null;
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				img = new Store_imgVO();
				img.setImg_id(rs.getInt("img_id"));
				img.setStore_id(rs.getInt("store_id"));
				img.setDate(rs.getDate("date"));
				img.setImg(rs.getBytes("img"));
				
				list.add(img);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		
		Store_imgVOJDBC jdbc = new Store_imgVOJDBC();
		Store_imgVO img = new Store_imgVO();
		img.setStore_id(1);
		
		FileInputStream in = new FileInputStream("/Users/chenguanlun/Desktop/圖檔/A1557651782.jpeg");
		byte[] b = new byte[(int)in.available()];
		in.read(b);
		img.setImg(b);
		img.setImg_id(1);
		in.close();
//		jdbc.insert(img);
//		jdbc.update(img);
//		jdbc.delete(2);
	
//		System.out.println(	jdbc.findByPrimaryKey(1));
		
		List<Store_imgVO> list = new ArrayList<>();
		list = jdbc.getAll();
		for(Store_imgVO vo : list) {
			System.out.println(vo);
		}
	}

}