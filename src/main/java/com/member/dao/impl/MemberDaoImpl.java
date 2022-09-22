package com.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.dao.MemberDao;
import com.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;


	public MemberDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean updatePermission(Integer id,Integer permission) {
		String sql = "UPDATE `member` SET `permission` = ? WHERE (`member_id` = ?);";
		try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
			ps.setObject(1,permission);
			ps.setObject(2, id);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Integer insert(MemberVO member) {
		final String sql = "insert into member(account,password,birthday,address,gender,email,nickname,phone) "
				+ "values(?,?,?,?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { "member_id" })) {
			ps.setString(1, member.getAccount());
			ps.setString(2, member.getPassword());
			ps.setObject(3, member.getBirthday());
			ps.setString(4, member.getAddress());
			ps.setInt(5, member.getGender());
			ps.setString(6, member.getEmail());
			ps.setString(7, member.getNickname());
			ps.setString(8, member.getPhone());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
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
	public Boolean login(String account,String password) {
		final String sql = "select 1 from member where account = '?' and password = '?';";
		try (PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
			ps.setString(1,account);
			ps.setString(2, password);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(MemberVO member) {
		String sql = "UPDATE member set  address=?, gender=?, email=?, nickname=?, phone=?" + "where member_id = ?;";
		int rowCount = 0;
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
		return rowCount == 1;
	}

	@Override
	public MemberVO selectByUsername(String username) {
		final String sql = "select * from member where account = ?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					MemberVO member1 = new MemberVO();
					member1.setMember_id(rs.getInt("member_id"));
					member1.setAccount(rs.getString("account"));
					member1.setPassword(rs.getString("password"));
					member1.setBirthday(rs.getObject("birthday", LocalDate.class));
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

	public MemberVO findByPrimaryKey(Integer id) {
		MemberVO m = null;
		String sql = "select member_id, account, password, birthday,address,gender,email,nickname,phone,register,permission "
				+ "from member where member_id = ?;";
		try (Connection connection = dataSource.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer member_id = rs.getInt(1);
				String account = rs.getString(2);
				String password = rs.getString(3);
				LocalDate birthday = rs.getObject(4, LocalDate.class);
				String address = rs.getString(5);
				Integer gender = rs.getInt(6);
				String email = rs.getString(7);
				String nickname = rs.getString(8);
				String phone = rs.getString(9);
				LocalDate register = rs.getObject(10, LocalDate.class);
				Integer permission = rs.getInt(11);
				m = new MemberVO(member_id, account, password, birthday, address, gender, email, nickname, phone,
						register, permission);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
		@Override
		public List<MemberVO> getAll() {
			List <MemberVO> member = new ArrayList<MemberVO>();
			String sql = "select * from member;";
			try(PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)){
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
					LocalDate register = rs.getObject(10,LocalDate.class);
					Integer permission = rs.getInt(11);
					MemberVO m = new MemberVO(member_id, account, password, birthday,address,gender,email,nickname,phone,register,permission);
					member.add(m);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return member;
		}

}
