package com.favorite.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FavoriteJNDIDAO implements FavoriteDAO{

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
	public List<FavoriteVO> getAll() {
		List <FavoriteVO> favorite = new ArrayList<FavoriteVO>();
		String sql = "select * from favorite;";
		try(Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer favorite_id = rs.getInt(1);
				Integer store_id = rs.getInt(2);
				Integer member_id = rs.getInt(3);
				
				FavoriteVO m = new FavoriteVO(favorite_id, store_id,member_id);
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
		String sql = "select favorite_id, store_id, member_id "
					+ "from favorite where favorite_id = ?;";
		try(Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Integer favorite_id = rs.getInt(1);
				Integer store_id = rs.getInt(1);
				Integer member_id = rs.getInt(1);
			
				m = new FavoriteVO(favorite_id, store_id,member_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void insert(FavoriteVO obj){
		int rowCount = 0;
		String sql = "Insert into favorite(store_id,member_id) "
				+ "values(?,?);";
			try(Connection connection = ds.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, obj.getStore_id());
				ps.setInt(2, obj.getMember_id());
				rowCount =  ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void update(FavoriteVO obj) {
		int rowCount = 0;
		String sql = "Update favorite set store_id = ?, member_id = ? where favorite_id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
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
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1,id);
				rowCount =  ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
