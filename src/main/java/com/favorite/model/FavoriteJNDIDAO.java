package com.favorite.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.store_img.model.Store_imgVO;

public class FavoriteJNDIDAO implements FavoriteDAO {

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
	public FavoriteVO findByPrimaryKey(Integer id) {
		FavoriteVO m = null;
		String sql = "select favorite_id, store_id, member_id " 
		+ "from favorite where favorite_id = ?;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer favorite_id = rs.getInt(1);
				Integer store_id = rs.getInt(1);
				Integer member_id = rs.getInt(1);
				System.out.println(favorite_id);
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
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
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
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public List<FavoriteVO> getAll(Integer member_id) {
		List<FavoriteVO> list = new ArrayList<FavoriteVO>();
		String sql = "select f.favorite_id,f.store_id,f.member_id,ss.`name` from favorite f "
				+ "join store_img s on " + "f.store_id = s.store_id " + "join store  ss on  "
				+ "f.store_id = ss.store_id " + "where f.member_id = ?; ";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, member_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FavoriteVO favoriteVO = new FavoriteVO();
				favoriteVO.setFavorite_id(rs.getInt("favorite_id"));
				favoriteVO.setStore_id(rs.getInt("store_id"));
				favoriteVO.setMember_id(rs.getInt("member_id"));
				favoriteVO.setName(rs.getString("name"));
				list.add(favoriteVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public FavoriteVO getStoreImgByStoreId(Integer member_id, Integer store_id) {
		
		String sql = "select f.favorite_id,f.store_id,f.member_id ,f.img,f.`name` , s.store_id from store s\n"
				+ "	join(select f.favorite_id,f.store_id,f.member_id ,s.img,ss.`name` from favorite f\n"
				+ "			join store_img s \n"
				+ "            on f.store_id = s.store_id\n"
				+ "			join store  ss \n"
				+ "			on f.store_id = ss.store_id\n"
				+ "		where f.member_id = ?) f\n"
				+ "	on s.store_id = f.store_id\n"
				+ "where f.member_id = ? and s.store_id = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, member_id);
			ps.setInt(2, member_id);
			ps.setInt(3, store_id);
			ResultSet rs = ps.executeQuery();
			FavoriteVO favoriteVO = new FavoriteVO();
			if (rs.next()) {
				favoriteVO.setImg(rs.getBytes("img"));
				return favoriteVO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//MAP
	@Override
	public List<FavoriteVO> getAllM(Integer id) {
		List <FavoriteVO> favorite = new ArrayList<FavoriteVO>();
		String sql = "select * from favorite where member_id=?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1,id);
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
	   public void deleteF(FavoriteVO obj) {
			String sql = "Delete from favorite where store_id = ? and member_id=?;";
			try(Connection connection = ds.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)){
					ps.setInt(1,obj.getStore_id());
					ps.setInt(2,obj.getMember_id());
					ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	

}
