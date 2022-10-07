package com.manager.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.manager.model.service.ManagerService;

public class ManagerJNDIDAO {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void updateLoginTime(Integer id) {
		String sql = "UPDATE `manager` SET `last_login_time` = ? WHERE (`manager_id` = ?);";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			LocalDateTime localDateTime = LocalDateTime.now();
			ppst.setObject(1, localDateTime);
			ppst.setObject(2, id);
			 ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ManagerVO> getAll() {
		List<ManagerVO> managerVOS = new ArrayList<>();
		String sql = "SELECT * FROM manager;";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				ManagerVO vo = new ManagerVO();
				vo.setManager_id(rs.getInt(1));
				vo.setAccount(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setLastLoginTime(rs.getObject(4, LocalDateTime.class));
				vo.setBirthday(rs.getObject(5, LocalDate.class));
				vo.setStatus(rs.getInt(6));
				managerVOS.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managerVOS;
	}

	public ManagerVO get(Integer manager_id) {
		ManagerVO vo = null;
		String sql = "select * from manager where manager_id = ?;";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, manager_id);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				vo = new ManagerVO();
				vo.setManager_id(rs.getInt(1));
				vo.setAccount(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setLastLoginTime(rs.getObject(4, LocalDateTime.class));
				vo.setBirthday(rs.getObject(5, LocalDate.class));
				vo.setStatus(rs.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public boolean add(ManagerVO obj) {
		int rows = 0;
		String sql = "INSERT INTO `manager` (`account`, `password`,`birthday`) VALUES (?, ?,?);";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, obj.getAccount());
			ppst.setObject(2, obj.getPassword());
			ppst.setObject(3, obj.getBirthday().toString());
			rows = ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean updateStatus(ManagerVO obj) {
		int rows = 0;
		String sql = "UPDATE `barjarjo`.`manager` SET  `status` = ? WHERE (`manager_id` = ?);";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, obj.getStatus());
			ppst.setObject(2, obj.getManager_id());
			rows = ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows > 0;
	}

	public boolean update(ManagerVO obj) {
		int rows = 0;
		String sql = "UPDATE `barjarjo`.`manager` SET `password` = ?, `status` = ? WHERE (`manager_id` = ?);";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, obj.getPassword());
			ppst.setObject(2, obj.getStatus());
			ppst.setObject(3, obj.getManager_id());
			rows = ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows > 0;
	}

	public boolean delete(Integer manager_id) {
		int rows = 0;
		String sql = "delete from manager where manager_id = ?;";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, manager_id);
			rows = ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows > 0;
	}

	public ManagerVO login(String account, String password) {
		ManagerVO vo = null;
		String sql = "select manager_id,last_login_time,birthday,status from manager where account=? and password= ?;";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, account);
			ppst.setObject(2, password);
			ResultSet rs = ppst.executeQuery();
			if (rs.next()) {
				vo = new ManagerVO();
				vo.setManager_id(rs.getInt(1));
				vo.setLastLoginTime(rs.getObject(2, LocalDateTime.class));
				vo.setBirthday(rs.getObject(3, LocalDate.class));
				vo.setStatus(rs.getInt(4));
				vo.setAccount(account);
				vo.setPassword(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public ManagerVO forgetPassword(String account, LocalDate birthday) {
		ManagerVO vo = null;
		String sql = "select manager_id ,password,last_login_time,status from manager where account=? and birthday= ?;";
		try (PreparedStatement ppst = ds.getConnection().prepareStatement(sql)) {
			ppst.setObject(1, account);
			ppst.setObject(2, birthday.toString());
			ResultSet rs = ppst.executeQuery();
			if (rs.next()) {
				vo = new ManagerVO();
				vo.setAccount(account);
				vo.setBirthday(birthday);
				vo.setManager_id(rs.getInt(1));
				vo.setPassword(rs.getString(2));
				vo.setLastLoginTime(rs.getObject(3, LocalDateTime.class));
				vo.setStatus(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}


}
