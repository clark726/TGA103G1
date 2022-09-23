package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreVOJDBC implements StoreDAO {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	@Override
	public boolean insert(StoreVO storeVo) {
		String sql = "insert into store( account , name , password , phone , email , address  , theme_id , dayoff , work_open , work_end , produce)\n"
				+ "values( ? , ? , ?, ?, ? ,? ,? ,? ,?, ? , ? );";
		int i = 0;
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getAccount());
			ps.setString(2, storeVo.getName());
			ps.setString(3, storeVo.getPassword());
			ps.setString(4, storeVo.getPhone());
			ps.setString(5, storeVo.getEmail());
			ps.setString(6, storeVo.getAddress());
			ps.setInt(7, storeVo.getTheme_id());
			ps.setString(8, storeVo.getDayoff());
			ps.setString(9, storeVo.getWork_open());
			ps.setString(10, storeVo.getWork_end());
			ps.setString(11, storeVo.getProduce());

			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i != 0;
	}

	@Override
	public void update(StoreVO storeVo) {
		String sql = "update store set account = ? , name = ?, password = ? , phone = ?, email = ? , address = ?  , lng = ? , lat = ? , theme_id = ? , dayoff = ? , work_open = ? , work_end = ? , produce = ?\n"
				+ "where store_id = ? ;";
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getAccount());
			ps.setString(2, storeVo.getName());
			ps.setString(3, storeVo.getPassword());
			ps.setString(4, storeVo.getPhone());
			ps.setString(5, storeVo.getEmail());
			ps.setString(6, storeVo.getAddress());
			ps.setString(7, storeVo.getLng());
			ps.setString(8, storeVo.getLat());
			ps.setInt(9, storeVo.getTheme_id());
			ps.setString(10, storeVo.getDayoff());
			ps.setString(11, storeVo.getWork_open());
			ps.setString(12, storeVo.getWork_end());
			ps.setString(13, storeVo.getProduce());
			ps.setInt(14, storeVo.getStore_id());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer store_id) {
		String sql = "delete from store where store_id = ?";

		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public StoreVO findByPrimaryKey(Integer store_id) {

		String sql = "select account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store\n" + "where store_id = ?;";
		StoreVO store = null;

		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(store_id);
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return store;
	}

	@Override
	public List<StoreVO> getAll() {

		List<StoreVO> list = new ArrayList<>();
		StoreVO store = null;
		String sql = "select store_id , account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store";

		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

	@Override
	public StoreVO findStoreAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreVO Login(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduce(StoreVO storevo) {
	}

	@Override
	public List<StoreVO> findStoreFrontpageBythemeId(Integer themeId) {
		// TODO Auto-generated method stub
		return null;
	}




}
