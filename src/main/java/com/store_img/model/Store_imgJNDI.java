package com.store_img.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Store_imgJNDI implements Store_imgDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Store_imgVO insert(Store_imgVO img) {
		String sql = "insert into store_img (store_id , img , status)\n" + "values(?, ? , ?);";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, img.getStore_id());
			ps.setBytes(2, img.getImg().getBytes());
			ps.setInt(3, img.getStatus1());
			ps.executeUpdate();
			System.out.println(img.getImg().getBytes());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void update(Store_imgVO img) {
		String sql = "update store_img set img = ? where store_id = ? and status = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setBytes(1, img.getImg().getBytes());
			ps.setInt(2, img.getStore_id());
			ps.setInt(3, img.getStatus1());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Integer img_id) {
		String sql = "delete from store_img where img_id = ?;";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, img_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Store_imgVO findByPrimaryKey(Integer img_id) {
		String sql = "select img_id , store_id , date , img from store_img\n" + "where img_id = ?;";
		Store_imgVO img = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, img_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				img = new Store_imgVO();
				img.setImg_id(rs.getInt("img_id"));
				img.setStore_id(rs.getInt("store_id"));
				img.setDate(rs.getDate("date"));
				img.setImg(new String(rs.getBytes("img")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}

	public List<Store_imgVO> getAll() {
		String sql = "select img_id , store_id , date , img from store_img";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();
		Store_imgVO img = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				img = new Store_imgVO();
				img.setImg_id(rs.getInt("img_id"));
				img.setStore_id(rs.getInt("store_id"));
				img.setDate(rs.getDate("date"));
				img.setImg(new String(rs.getBytes("img")));

				list.add(img);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 取資料時用
	public Store_imgVO findImgByStoreIdandSratus(Integer store_id, Integer status) {
		String sql = "SELECT img FROM store_img\n" + "where store_id = ? and status = ?;";
		Store_imgVO img = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ps.setInt(2, status);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				img = new Store_imgVO();
				img.setImg(new String(rs.getBytes("img")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;

	}

	@Override
	public List<Store_imgVO> getbackInformation(String account) {
		String sql = "select s.name , s.dayoff , s.work_open , s.work_end , s.produce , i.img  from store s\n"
				+ "left join store_img i\n" + "on s.store_id = i.store_id\n" + "where s.account = ?";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store_imgVO img = new Store_imgVO();
				img.setName(rs.getString("name"));
				img.setDayoff(rs.getString("dayoff"));
				img.setWork_open(rs.getString("work_open"));
				img.setWork_end(rs.getString("work_end"));
				img.setProduce(rs.getString("produce"));
				// 為了第一次要取店家名字給前端
				if (rs.getBytes("img") != null) {
					img.setImg(new String(rs.getBytes("img")));
				}
				list.add(img);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id) {
		String sql = "SELECT img , status FROM store_img\n" + "where store_id = ?";
		List<Store_imgVO> list = new ArrayList<Store_imgVO>();
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store_imgVO img = new Store_imgVO();
				img = new Store_imgVO();
				img.setImg(new String(rs.getBytes("img")));
				img.setStatus1(rs.getInt("status"));
				list.add(img);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
