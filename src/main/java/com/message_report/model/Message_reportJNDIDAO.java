package com.message_report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class Message_reportJNDIDAO {

	
	private static DataSource ds = null;
	static {
		try {
			 Context ctx = new InitialContext();
			 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getMessageReportByMessageId(Integer messageId) {
	       List<Integer> ids = new ArrayList<>();
	        String sql = "select message_report_id from message_report where message_id = ?;";
	        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
	        	ppst.setObject(1, messageId);
	            ResultSet rs = ppst.executeQuery();
	            while (rs.next()){
	                ids.add(rs.getInt(1));
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return ids;
	}
	
	
	public List<Message_reportVO> getAll() {
		List <Message_reportVO> Message_reportVO = new ArrayList<Message_reportVO>();
		String sql = "SELECT * FROM message_report;";
		try(Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Message_reportVO.add(new Message_reportVO(rs.getInt(1),
						rs.getInt(2),rs.getInt(3),rs.getString(4),
						rs.getObject(5,LocalDate.class),rs.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Message_reportVO;
	}
	
	public boolean update(Integer ID) {
		int rowCount = 0;
		String sql = "UPDATE `message_report` SET `status` = 1 WHERE (`message_report_id` = ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setObject(1, ID);
				rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 0;
	}
}
