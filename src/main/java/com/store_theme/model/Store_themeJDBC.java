package com.store_theme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class Store_themeJDBC implements Store_themeDAO{

	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";


	public Store_themeVO findByPrimaryKey(Integer theme_id) {
		Store_themeVO store_themeVO = null;
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
				PreparedStatement ppStatement = connection.prepareStatement("select * from store_theme where  theme_id = ? ;");){
			ppStatement.setObject(1, theme_id);
			ResultSet rsResultset = ppStatement.executeQuery();
			if(rsResultset.next()) {
				store_themeVO = new Store_themeVO();
				store_themeVO.setTheme_id(rsResultset.getInt(1));
				store_themeVO.setTheme_name(rsResultset.getString(2));
				store_themeVO.setIntroduce(rsResultset.getString(3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return store_themeVO;
	}

	public List<Store_themeVO> getAll() {
		List<Store_themeVO> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
				PreparedStatement ppStatement = connection.prepareStatement("select * from store_theme;");){
			ResultSet rsResultset = ppStatement.executeQuery();
			while(rsResultset.next()) {
				Store_themeVO store_themeVO = new Store_themeVO();
				store_themeVO.setTheme_id(rsResultset.getInt(1));
				store_themeVO.setTheme_name(rsResultset.getString(2));
				store_themeVO.setIntroduce(rsResultset.getString(3));
				list.add(store_themeVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
