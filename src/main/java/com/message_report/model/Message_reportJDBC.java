package com.message_report.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Message_reportJDBC implements Message_reportInterface<Message_reportVO> {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/barjarjo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	@Override
	public boolean insert(Message_reportVO obj) {
		int rowCount = 0;
		String sql = "Insert into message_report(member_id,message_id,reason,date,status) "
				+ "values(?,?,?,?,?);";
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, obj.getMember_id());
			ps.setInt(2, obj.getMessage_id());
			ps.setString(3, obj.getReason());
			ps.setObject(4, obj.getDate());
			ps.setInt(5,obj.getStatus());
			
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return rowCount == 1;
	}

	@Override
	public boolean update(Message_reportVO obj) {
		int rowCount = 0;
		String sql = "Update message_report set member_id =? ,message_id=?, reason =? ,date=?,status=? "
				+ "where message_report_id = ?;";
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, obj.getMember_id());
			ps.setInt(2, obj.getMessage_id());
			ps.setString(3, obj.getReason());
			ps.setObject(4, obj.getDate());
			ps.setInt(5,obj.getStatus());
			ps.setInt(6, obj.getMessage_report_id());
			
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public boolean delete(Integer id ,Integer member_id,Integer message_id) {
		int rowCount = 0;
		String sql = "Delete from message_report where message_report_id = ? and member_id = ? and message_id = ? ;";
		try(Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.setInt(2, member_id);
			ps.setInt(3, message_id);
			
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount ==1;
	}

	@Override
	public Message_reportVO findByPrimaryKey(Integer id) {
		Message_reportVO mr = null;
		String sql = "select message_report_id,member_id,message_id,reason,date,status from message_report "
				+ "where message_report_id = ?;";
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer message_report_id = rs.getInt(1);
				Integer member_id = rs.getInt(2);
				Integer message_id = rs.getInt(3);
				String reason = rs.getString(4);
				LocalDate date = rs.getObject(5,LocalDate.class);
				Integer status = rs.getInt(6);
				
				mr = new Message_reportVO(message_report_id,member_id,message_id,reason,date,status);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mr;
	}

	@Override
	public List<Message_reportVO> getAll() {
		List <Message_reportVO> list = new ArrayList<Message_reportVO>();
		
		String sql = "select * from message_report;";
		// TODO Auto-generated method stub
		try(Connection connection = DriverManager.getConnection(url,userid,passwd);
			PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer message_report_id = rs.getInt(1);
				Integer member_id = rs.getInt(2);
				Integer message_id = rs.getInt(3);
				String reason = rs.getString(4);
				LocalDate date = rs.getObject(5,LocalDate.class);
				Integer status = rs.getInt(6);
				
				
				Message_reportVO m = new Message_reportVO(message_report_id,member_id,message_id,reason,date,status);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


}
