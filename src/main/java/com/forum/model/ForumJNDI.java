package com.forum.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ForumJNDI {
	private static DataSource ds = null;
	static {
		try {
			 Context ctx = new InitialContext();
			 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public List<ForumVO> getAll(){
		List<ForumVO> list = new ArrayList<ForumVO>();
		try(Connection conn = ds.getConnection();
			PreparedStatement ppst = conn.prepareStatement("select * from forum;");
				ResultSet rs = ppst.executeQuery()){
			while(rs.next()) {
				ForumVO f = new ForumVO();
				f.setForum_id(rs.getInt(1));
				f.setMember_id(rs.getInt(2));
				f.setContent(rs.getString(3));
				f.setDate(rs.getObject(4,LocalDateTime.class));
				f.setTitle(rs.getString(5));
				f.setLike(rs.getInt(6));
				f.setLook(rs.getInt(7));
				f.setMessage(rs.getInt(8));
				f.setStatus(rs.getInt(9));
				list.add(f);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
