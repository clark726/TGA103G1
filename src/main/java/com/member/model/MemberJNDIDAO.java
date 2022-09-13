package com.member.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberJNDIDAO implements MemberDAO{

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
	public List<MemberVO> getAll() {
		List <MemberVO> member = new ArrayList<MemberVO>();
		String sql = "select * from member;";
		try(Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer member_id = rs.getInt(1);
				String account = rs.getString(2);
				String password = rs.getString(3);
				LocalDate birthday = rs.getObject(4,LocalDate.class);
				String address = rs.getString(5);
				Integer gender = rs.getInt(6);
				String email = rs.getString(7);
				String nickname = rs.getString(8);
				String phone = rs.getString(9);
				LocalDateTime register = rs.getObject(10,LocalDateTime.class);
				Integer permission = rs.getInt(11);
				MemberVO m = new MemberVO(member_id, account, password, birthday,address,gender,email,nickname,phone,register,permission);
				member.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public MemberVO findByPrimaryKey(Integer id) {
		MemberVO m = null;
		String sql = "select member_id, account, password, birthday,address,gender,email,nickname,phone,register,permission "
					+ "from member where member_id = ?;";
		try(Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Integer member_id = rs.getInt(1);
				String account = rs.getString(2);
				String password = rs.getString(3);
				LocalDate birthday = rs.getObject(4,LocalDate.class);
				String address = rs.getString(5);
				Integer gender = rs.getInt(6);
				String email = rs.getString(7);
				String nickname = rs.getString(8);
				String phone = rs.getString(9);
				LocalDateTime register = rs.getObject(10,LocalDateTime.class);
				Integer permission = rs.getInt(11);
				m = new MemberVO(member_id, account, password, birthday,address,gender,email,nickname,phone,register,permission);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void insert(MemberVO obj){
		int rowCount = 0;
		String sql = "Insert into member(account,password,birthday,address,gender,email,nickname,phone,permission) "
				+ "values(?,?,?,?,?,?,?,?,?);";
			try(Connection connection = ds.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setString(1, obj.getAccount());
				ps.setString(2, obj.getPassword());
				ps.setObject(3, obj.getBirthday());;
				ps.setString(4, obj.getAddress());
				ps.setInt(5, obj.getGender());
				ps.setString(6, obj.getEmail());
				ps.setString(7, obj.getNickname());
				ps.setString(8, obj.getPhone());
				ps.setInt(9, obj.getPermission());
				rowCount =  ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void update(MemberVO obj) {
		int rowCount = 0;
		String sql = "Update member set account = ?, password = ? , birthday = ?,address = ?,gender = ?, "
				+ "email = ?,nickname = ? ,phone = ?, register = ? , permission = ? where member_id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setString(1, obj.getAccount());
				ps.setString(2, obj.getPassword());
				ps.setObject(3,	obj.getBirthday());
				ps.setString(4, obj.getAddress());
				ps.setInt(5,obj.getGender());
				ps.setString(6, obj.getEmail());
				ps.setString(7, obj.getNickname());
				ps.setString(8, obj.getPhone());
				ps.setObject(9, obj.getRegister());
				ps.setInt(10, obj.getPermission());
				ps.setInt(11, obj.getMember_id());
				rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void delete(Integer id) {
		int rowCount = 0;
		String sql = "Delete from member where member_id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1,id);
				rowCount =  ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}