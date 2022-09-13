package com.favorite.model;

import java.sql.*;
import java.time.*;
import java.util.*;

public class FavoriteJDBCDAO implements FavoriteDAO {
	String url = "jdbc:mysql://localhost:3306/barjarjo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	@Override
	public List<FavoriteVO> getAll() {
		List<FavoriteVO> favorite = new ArrayList<FavoriteVO>();
		String sql = "select * from favorite;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer favorite_id = rs.getInt(1);
				Integer store_id = rs.getInt(2);
				Integer member_id = rs.getInt(3);

				FavoriteVO m = new FavoriteVO(favorite_id, store_id, member_id);
				favorite.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favorite;
	}

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
	public void insert(FavoriteVO obj) {
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

	}

	@Override
	public void update(FavoriteVO obj) {
		int rowCount = 0;
		String sql = "Update favorite set store_id = ?, member_id = ? where favorite_id = ?;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getStore_id());
			ps.setInt(2, obj.getMember_id());
			ps.setInt(3, obj.getFavorite_id());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		int rowCount = 0;
		String sql = "Delete from favorite where favorite_id = ?;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
