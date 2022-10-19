package com.member.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.common.HibernateUtil;
import com.member.dao.MemberDao;
import com.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public MemberDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean updatePermission(Integer id,Integer permission) {
		String sql = "UPDATE `member` SET `permission` = ? WHERE (`member_id` = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
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
		
		Session session = sessionFactory.openSession();
//		final String sql = "insert into member(account,password,birthday,address,gender,email,nickname,phone) "
//				+ "values(?,?,?,?,?,?,?,?);";
			
//			(Connection conn = dataSource.getConnection();
//					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "member_id" }))
			try{
				Transaction transaction = session.beginTransaction();
				session.persist(member);
				transaction.commit();
				return 1;
//					ps.setString(1, member.getAccount());
//					ps.setString(2, member.getPassword());
//					ps.setObject(3, member.getBirthday());
//					ps.setString(4, member.getAddress());
//					ps.setInt(5, member.getGender());
//					ps.setString(6, member.getEmail());
//					ps.setString(7, member.getNickname());
//					ps.setString(8, member.getPhone());
//					ps.executeUpdate();
//			try (ResultSet rs = ps.getGeneratedKeys()) {
//				if (rs.next()) {
//					Integer id = rs.getInt(1);
//					return id;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					}
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean login(String account,String password) {
		final String sql = "select 1 from member where account = ? and password = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1,account);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
		
				return rs.getInt(1) == 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(MemberVO member) {
//		String sql = "UPDATE member set  address=?, gender=?, email=?, nickname=?, phone=?" + "where member_id = ?;";
		Session session = sessionFactory.openSession();
		
		try 
//		(Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
		{
			Transaction transaction = session.beginTransaction();
			final MemberVO oldMember = session.load(MemberVO.class, member.getMember_id());
			oldMember.setAddress(member.getAddress());
			oldMember.setGender(member.getGender());
			oldMember.setEmail(member.getEmail());
			oldMember.setNickname(member.getNickname());
			oldMember.setPhone(member.getPhone());
			transaction.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MemberVO selectByUsername(String username) {
		final String sql = "select member_id,account,password,birthday,address,gender,email,nickname,phone from member where account = ?;";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					MemberVO member1 = new MemberVO();
					member1.setMember_id(rs.getInt(1));
					member1.setAccount(rs.getString(2));
					member1.setPassword(rs.getString(3));
					member1.setBirthday(rs.getObject(4, LocalDate.class));
					member1.setAddress(rs.getString(5));
					member1.setGender(rs.getInt(6));
					member1.setEmail(rs.getString(7));
					member1.setNickname(rs.getString(8));
					member1.setPhone(rs.getString(9));
					
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
		try (Connection connection = dataSource.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
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
			try(Connection connection = dataSource.getConnection();
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

		@Override
		public boolean updatePassword(MemberVO member) {
//			String sql = "UPDATE member set  password = ? where member_id = ?;";
			Session session = sessionFactory.openSession();
			try 
//			(Connection conn = dataSource.getConnection(); 
//					PreparedStatement ps = conn.prepareStatement(sql)) 
			{
				Transaction transaction = session.beginTransaction();
				final MemberVO oldMember = session.load(MemberVO.class, member.getMember_id());
				
				oldMember.setPassword(member.getPassword());
				oldMember.setMember_id(member.getMember_id());
				transaction.commit();
				return true;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
				return false;
			}
			
		}

		@Override
		public MemberVO selectForPass(String account, String email) {
			final String sql = "select member_id,account,password,birthday,address,gender,email,nickname,phone "
					+ "from member where account = ? and email = ?;";
			try (Connection conn = dataSource.getConnection(); 
					PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, account);
				ps.setString(2, email);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					MemberVO member1 = new MemberVO();
					member1.setMember_id(rs.getInt(1));
					member1.setAccount(rs.getString(2));
					member1.setPassword(rs.getString(3));
					member1.setBirthday(rs.getObject(4, LocalDate.class));
					member1.setAddress(rs.getString(5));
					member1.setGender(rs.getInt(6));
					member1.setEmail(rs.getString(7));
					member1.setNickname(rs.getString(8));
					member1.setPhone(rs.getString(9));
					
					return member1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		public boolean updatePassByUsername(MemberVO member) {
			String sql = "Update `member` set `password` = ? where `account` = ?;";
		
			int rowCount = 0;
			
			try (Connection con = dataSource.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				System.out.println("連線成功");
				
				pstmt.setString(1, member.getPassword());
				pstmt.setString(2, member.getAccount());
				
				rowCount = pstmt.executeUpdate();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rowCount == 1;
		}
		
}
