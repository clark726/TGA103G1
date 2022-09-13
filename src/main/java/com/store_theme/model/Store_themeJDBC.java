package com.store_theme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Store_themeJDBC implements Store_themeDAO{

	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	@Override
	public void insert(Store_themeVO theme) {

		String sql = "insert into store_theme(theme_id , theme_name , introduce)\n"
				+ "values(? , ? , ?);";
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, theme.getTheme_id());
			ps.setString(2, theme.getTheme_name());
			ps.setString(3, theme.getIntroduce());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Store_themeVO store_theme) {

		String sql = "";
	}

	@Override
	public void delete(Integer theme_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store_themeVO findByPrimaryKey(Integer theme_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store_themeVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
