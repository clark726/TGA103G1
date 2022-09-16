package com.product.model;

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

import com.product_img.model.Product_imgVO;

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
	public Integer insert(ProductVO product) {
		String sql = "insert into product (name , price , store_id , description , type_id , stock )\n"
				+ "values(?,?,?,?,?,?);";
		
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql , new String[] {"product_id"})){
		
			ps.setString(1,product.getName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getStore_id());
			ps.setString(4 , product.getDescription());
			ps.setInt(5, product.getType_id());
			ps.setInt(6, product.getStock());
			ps.executeUpdate();
			
			//自動取編號
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				return  rs.getInt(1);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Integer update(ProductVO product) {
		String sql = "update product set name = ? , price = ? , store_id = ? , description = ? , type_id = ? , stock = ? , status = ?\n"
				+ "where product_id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, product.getName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getStore_id());
			ps.setString(4 , product.getDescription());
			ps.setInt(5, product.getType_id());
			ps.setInt(6, product.getStock());
			ps.setInt(7, product.getStatus());
			ps.setInt(8, product.getProduct_id());
			
			ps.executeUpdate();
			return product.getProduct_id();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void delete(Integer product_id) {

		String sql = "delete from product where product_id = ? ";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, product_id);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {

		String sql = "select product_id , name , price, store_id ,description, type_id , stock , status, date \n"
				+ "from product\n"
				+ "where product_id = ?;";
		ProductVO product = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, product_id);
		
			ResultSet rs = 	ps.executeQuery();
			while(rs.next()) {
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public List<ProductVO> getAll() {

		String sql = "select p.product_id , p.name , price, store_id ,description, type_id , stock , status, p.date , img \n"
				+ "from product p\n"
				+ "	left join product_img m\n"
				+ "    on p.product_id = m.product_id;";
		List<ProductVO> list = new ArrayList<>();
		ProductVO product = null;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ProductVO findProductid(Integer product_id) {

		String sql = "select p.product_id , p.name , price, store_id ,description, type_id , stock , status, p.date , img \n"
				+ "from product p\n"
				+ "	left join product_img m\n"
				+ "    on p.product_id = m.product_id;";
		ProductVO img = null;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				){
			
			ps.setInt(1, product_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				img = new ProductVO();
				List<Object> listobj = new ArrayList<Object>();
				listobj.add(rs.getObject("img"));
				//取照片
				img.setImgs(listobj);
				
				img.setProduct_id(rs.getInt("product_id"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return img;
	}

	public static void main(String[] args) {
		
		ProductVOJDBC jdbc = new ProductVOJDBC();
		ProductVO p1 = new ProductVO();
		p1.setProduct_id(10);
		p1.setName("茶調酒");
		p1.setPrice(800);
		p1.setStore_id(2);
		p1.setDescription("超好喝2");
		p1.setType_id(1);
		p1.setStock(20);
//		p1.setStatus(1);
		
//		jdbc.insert(p1);
//		jdbc.update(p1);
		ProductVO p = jdbc.findByPrimaryKey(1);
		System.out.println(p);
//	
//		List<ProductVO> list = new ArrayList<>();
//		list = jdbc.getAll();
//		
//		for(ProductVO product : list) {
//			System.out.println(product);
//		}
	}
}
