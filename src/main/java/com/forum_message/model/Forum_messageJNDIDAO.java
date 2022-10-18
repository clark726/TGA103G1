package com.forum_message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Forum_messageJNDIDAO implements Forum_messageDAO {
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
	public List<Forum_messageVO> getAll() {
		List<Forum_messageVO> forum_message = new ArrayList<Forum_messageVO>();
		String sql = "select * from forum_message;";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer message_id = rs.getInt(1);
				Integer member_id = rs.getInt(2);
				Integer forum_id = rs.getInt(3);
				String content = rs.getString(4);
				LocalDateTime date = rs.getObject(5,LocalDateTime.class);

				Forum_messageVO m = new Forum_messageVO(message_id, member_id, forum_id, content, date);
				forum_message.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forum_message;
	}

	@Override
	public Forum_messageVO findByPrimaryKey(Integer id) {
		Forum_messageVO m = null;
		String sql = "select * from forum_message where message_id = ?;";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer message_id = rs.getInt(1);
				Integer member_id = rs.getInt(2);
				Integer forum_id = rs.getInt(3);
				String context = rs.getString(4);
				LocalDateTime date = rs.getObject(5,LocalDateTime.class);

				m = new Forum_messageVO(message_id,member_id,forum_id,context,date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void insert(Forum_messageVO obj) {
		int rowCount = 0;
		String sql = "Insert into forum_message(member_id,forum_id,content) " + "values(?,?,?);";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getMember_id());
			ps.setInt(2, obj.getForum_id());
			ps.setString(3, obj.getContent());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Forum_messageVO obj) {
		int rowCount = 0;
		String sql = "Update forum_message set member_id = ?, forum_id = ?,content = ?,date = ? where message_id = ?;";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getMember_id());
			ps.setInt(2, obj.getForum_id());
			ps.setString(3, obj.getContent());
			ps.setObject(2, obj.getDate());
			ps.setInt(3, obj.getMessage_id());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateMeassage(Integer forumId ) {
		int rowCount = 0;
		String sql = "update forum set message = message+1  where forum_id=?";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, forumId);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount > 0;
	}

	@Override
	public void delete(Integer id) {
		int rowCount = 0;
		String sql1 = "SET FOREIGN_KEY_CHECKS=0;";
		String sql2 = "Delete from forum_message where message_id = ?;";
		String sql3 = "SET FOREIGN_KEY_CHECKS=1;";
		try (Connection connection = ds.getConnection();
			PreparedStatement ps1 = connection.prepareStatement(sql1);
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				PreparedStatement ps3 = connection.prepareStatement(sql3);) {
			ps1.executeUpdate();
			ps2.setObject(1, id);
			ps2.executeUpdate();
			ps3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> beforeDelete(Integer messageId) {
		List<Integer> messageIds = new ArrayList<Integer>();
		String sql = "select message_id from forum_message where forum_id = (select forum_id from forum_message where message_id = ?);";
		try (Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setObject(1, messageId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				messageIds.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageIds;
	}
}
