package com.favorite.model;

import java.sql.*;
import java.time.*;
import java.util.*;

import com.store_img.model.Store_imgVO;

public class FavoriteJDBCDAO implements FavoriteDAO {
	String url = "jdbc:mysql://localhost:3306/barjarjo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";


	@Override
	public FavoriteVO findByPrimaryKey(Integer id) {
		FavoriteVO m = null;
		String sql = "select favorite_id,store_id,member_id " + "from favorite where favorite_id = ?;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer favorite_id = rs.getInt(1);
				Integer store_id = rs.getInt(2);
				Integer member_id = rs.getInt(3);

				m = new FavoriteVO(favorite_id, store_id, member_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public boolean insert(FavoriteVO obj) {
		int rowCount = 0;
		String sql = "Insert into favorite(store_id,member_id) " + "values(?,?);";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getStore_id());
			ps.setInt(2, obj.getMember_id());

			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}


	@Override
	public boolean delete(Integer id) {
		int rowCount = 0;
		String sql = "Delete from favorite where favorite_id = ?;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public List<FavoriteVO> getAll(Integer member_id) {
		
		return null;
	}


	@Override
	public FavoriteVO getStoreImgByStoreId(Integer member_id, Integer store_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoriteVO> getAllM(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteF(FavoriteVO obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store_imgVO getStoreImgByMemberId(Integer member_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
