package com.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.dao.MemberDao;
import com.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDao{
	private DataSource dataSource;
	
	public MemberDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Integer insert(MemberVO member) {
		final String sql = "insert into member(account,password,birthday,address,gender,email,nickname,phone) "
				+ "values(?,?,?,?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, new String[] {"member_id"})){
			ps.setString(1, member.getAccount());
			ps.setString(2, member.getPassword());
			ps.setObject(3, member.getBirthday());
			ps.setString(4, member.getAddress());
			ps.setInt(5, member.getGender());
			ps.setString(6, member.getEmail());
			ps.setString(7, member.getNickname());
			ps.setString(8, member.getPhone());
			ps.executeUpdate();
			
			try (ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					Integer id = rs.getInt(1);
					return id;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public MemberVO login(MemberVO member) {
		final String sql = "select * from member where account = ? and password = ?;";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql,new String[] {"ID"})){
			ps.setString(1, member.getAccount());
			ps.setString(2, member.getPassword());
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					MemberVO member1 = new MemberVO();
					member1.setMember_id(rs.getInt("member_id"));
					member1.setAccount(rs.getString("account"));
					member1.setPassword(rs.getString("password"));
					member1.setBirthday(rs.getObject("birthday",LocalDate.class));
					member1.setAddress(rs.getString("address"));
					member1.setGender(rs.getInt("gender"));
					member1.setEmail(rs.getString("email"));
					member1.setNickname(rs.getString("nickname"));
					member1.setPhone(rs.getString("phone"));
			
					return member1;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean update(MemberVO member) {
		String sql = "UPDATE member set  address=?, gender=?, email=?, nickname=?, phone=?"
				+ "where member_id = ?;";
		int rowCount = 0;
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, member.getAddress());
			ps.setInt(2, member.getGender());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getNickname());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getMember_id());
			
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount ==1;
	}
	@Override
	public MemberVO selectByUsername(String username) {
		final String sql = "select * from member where account = ?;";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, username);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
				MemberVO member1 = new MemberVO();
				member1.setMember_id(rs.getInt("member_id"));
				member1.setAccount(rs.getString("account"));
				member1.setPassword(rs.getString("password"));
				member1.setBirthday(rs.getObject("birthday",LocalDate.class));
				member1.setAddress(rs.getString("address"));
				member1.setGender(rs.getInt("gender"));
				member1.setEmail(rs.getString("email"));
				member1.setNickname(rs.getString("nickname"));
				member1.setPhone(rs.getString("phone"));
				
				return member1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

