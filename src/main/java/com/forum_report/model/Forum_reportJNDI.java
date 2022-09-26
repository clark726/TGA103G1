package com.forum_report.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Forum_reportJNDI {
	private static DataSource ds = null;
	static {
		try {
			 Context ctx = new InitialContext();
			 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public List<Forum_reportVO> getAll(){
		List<Forum_reportVO> list = new ArrayList<>();
		String sql = "SELECT * FROM forum_report;";
		try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql);
				ResultSet rsResultset = ppst.executeQuery();){
			while(rsResultset.next()) {
				Forum_reportVO vo = new Forum_reportVO();
				vo.setForum_report_id(rsResultset.getInt(1));
				vo.setMember_id(rsResultset.getInt(2));
				vo.setForum_id(rsResultset.getInt(3));
				vo.setReason(rsResultset.getString(4));
				vo.setDate(rsResultset.getObject(5,LocalDateTime.class));
				vo.setStatus(rsResultset.getInt(6));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean update(Integer forum_report_id) {
		int row = 0;
		try(PreparedStatement ppst = ds.getConnection().prepareStatement("UPDATE `forum_report` SET `status` = '1' WHERE (`forum_report_id` = ?);")){
			ppst.setObject(1, forum_report_id);
			row = ppst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return row ==1;
	}
	
}
