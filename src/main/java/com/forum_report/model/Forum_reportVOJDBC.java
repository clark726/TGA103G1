package com.forum_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Forum_reportVOJDBC implements Forum_reportDAO{

	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	
	@Override
	public void insert(Forum_reportVO report) {
		String sql = "insert into forum_report( member_id , forum_id , reason ) \n"
				+ "values( ? , ? , ? );";
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, report.getMember_id());
			ps.setInt(2, report.getForum_id());
			ps.setString(3, report.getReason());
			
			ps.executeUpdate();
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Forum_reportVO report) {

		String sql = "update forum_report set  member_id = ? , forum_id = ? , reason = ? , status = ?\n"
				+ "where forum_report_id = ?;";
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, report.getMember_id());
			ps.setInt(2, report.getForum_id());
			ps.setString(3, report.getReason());
			ps.setInt(4, report.getStatus());
			ps.setInt(5, report.getForum_report_id());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer report_id) {

		String sql = "delete from forum_report where forum_report_id = ?;";
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, report_id);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Forum_reportVO findByPrimaryKey(Integer report_id) {
		String sql = "select forum_report_id , member_id , forum_id , reason , date , status\n"
				+ "from forum_report\n"
				+ "where forum_report_id  = ?;";
		Forum_reportVO forum = null;
		
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, report_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				forum = new Forum_reportVO();
				forum.setForum_report_id(rs.getInt("forum_report_id"));
				forum.setMember_id(rs.getInt("member_id"));
				forum.setForum_id(rs.getInt("forum_id"));
				forum.setReason(rs.getString("reason"));
				forum.setDate(rs.getObject("date",LocalDateTime.class));
				forum.setStatus(rs.getInt("status"));

			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return forum;
	}

	@Override
	public List<Forum_reportVO> getAll() {
		
		String sql = "select forum_report_id , member_id , forum_id , reason , date , status\n"
				+ "from forum_report";
		Forum_reportVO forum = null;
		List<Forum_reportVO> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url , userid , passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				forum = new Forum_reportVO();
				forum.setForum_report_id(rs.getInt("forum_report_id"));
				forum.setMember_id(rs.getInt("member_id"));
				forum.setForum_id(rs.getInt("forum_id"));
				forum.setReason(rs.getString("reason"));
				forum.setDate(rs.getObject("date",LocalDateTime.class));
				forum.setStatus(rs.getInt("status"));
				
				list.add(forum);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static void main(String[] args) {
		
		Forum_reportVOJDBC jdbc = new Forum_reportVOJDBC();
		Forum_reportVO vo = new Forum_reportVO();
		
		vo.setForum_id(1);
		vo.setMember_id(1);
		vo.setReason("12345");;
//		jdbc.insert(vo);
		vo.setStatus(1);
		vo.setForum_report_id(1);
//		jdbc.update(vo);
		
//		jdbc.delete(1);
		
//		System.out.println(jdbc.findByPrimaryKey(2));
		List<Forum_reportVO> list = jdbc.getAll();
		for(Forum_reportVO vo1 : list) {
			System.out.println(vo1);
		}
	}
	
}
