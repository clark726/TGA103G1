package com.store.model;

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

public class StoreJNDI implements StoreDAO {

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
	public boolean insert(StoreVO storeVo) {
		String sql = "insert into store( account , name , password , phone , email , address  , theme_id , dayoff , work_open , work_end , produce)\n"
				+ "values( ? , ? , ?, ?, ? ,? ,? ,? ,?, ? , ? );";
		int i = 0;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

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
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

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
	public void updateProduce(StoreVO storeVo) {
		String sql = "update store set dayoff = ? , work_open = ? , work_end = ? , produce = ?\n"
				+ "where store_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

		
			ps.setString(1, storeVo.getDayoff());
			ps.setString(2, storeVo.getWork_open());
			ps.setString(3, storeVo.getWork_end());
			ps.setString(4, storeVo.getProduce());
			ps.setInt(5, storeVo.getStore_id());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer store_id) {
		String sql = "delete from store where store_id = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
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

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
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

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
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
		String sql = "select store_id ,account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store where account = ?;";

		StoreVO store = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);
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

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return store;
	}

	@Override
	public StoreVO Login(String account, String password) {

		String sql = "select account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce from store\n"
				+ "where account =  ? and password = ?;";

		StoreVO store = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
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

}
