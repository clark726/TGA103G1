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
	
	public List<Object[]> getAllAndForumId(){
		List <Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT message_report_id,m.member_id,m.message_id,reason,m.date,status,forum_id FROM barjarjo.message_report m join forum_message f on m.message_id = f.message_id where status = 0;";
		try(PreparedStatement ps = ds.getConnection().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Object[] obj = new Object[7];
				int x=0;
				obj[x++]=rs.getObject(1);
				obj[x++]=rs.getObject(2);
				obj[x++]=rs.getObject(3);
				obj[x++]=rs.getObject(4);
				obj[x++]=rs.getObject(5);
				obj[x++]=rs.getObject(6);
				obj[x++]=rs.getObject(7);
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Integer> getForumIdByStatus(Integer id) {
		List<Integer> list = new ArrayList<Integer>();
        String sql = "select forum_id from forum_message where message_id in (select message_id from message_report where status = ?);";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
        	ppst.setObject(1, id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                list.add(rs.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
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
		String sql = "SELECT * FROM message_report where status = 0;";
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
