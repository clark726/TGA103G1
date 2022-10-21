package com.forum_report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.vo.MemberVO;


public class Forum_reportJNDI implements Forum_reportDAO{
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
	public MemberVO findMemberByForumId(Integer id) {
		MemberVO vo = null;
		String sql = "select * from member where member.member_id = (select member_id from forum where forum_id = ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ppst = connection.prepareStatement(sql)
				){
			ppst.setObject(1, id);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMember_id(rs.getInt(1));
				vo.setAccount(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setBirthday(rs.getObject(4,LocalDate.class));
				vo.setAddress(rs.getString(5));
				vo.setGender(rs.getInt(6));
				vo.setEmail(rs.getString(7));
				vo.setNickname(rs.getString(8));
				vo.setPhone(rs.getString(9));
				vo.setRegister(rs.getObject(10,LocalDate.class));
				vo.setPermission(rs.getInt(11));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public int findForumId(Integer id) {
		int forumId = 0;
		try(PreparedStatement ppst = ds.getConnection().prepareStatement("select forum_id from forum_report where forum_report_id = ?;")){
			ppst.setObject(1, id);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				forumId = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forumId;
	}
	
	public List<Forum_reportVO> getAll(){
		List<Forum_reportVO> list = new ArrayList<>();
		String sql = "SELECT * FROM forum_report where status = 0;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ppst = connection.prepareStatement(sql);
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
		String sql="UPDATE `forum_report` SET status = 1 WHERE (`forum_report_id` = ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ppst = connection.prepareStatement(sql)){
			ppst.setObject(1, forum_report_id);
			row = ppst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return row ==1;
	}
	
	public List<Integer> getFourmIds(Integer id){
		List<Integer> list = new ArrayList<>();
		String sql = "select forum_report_id from forum_report where forum_id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ppst = connection.prepareStatement(sql);){
				ppst.setObject(1, id);
				ResultSet rsResultset = ppst.executeQuery();
			while(rsResultset.next()) {
				list.add(rsResultset.getInt(1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insert(Forum_reportVO report) {
		int row = 0;
		String sql="insert into forum_report (member_id, forum_id , reason) values (?,?,?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ppst = connection.prepareStatement(sql)){
			ppst.setObject(1, report.getMember_id());
			ppst.setObject(2, report.getForum_id());
			ppst.setObject(3, report.getReason());
			row = ppst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return row ==1;
	}

	@Override
	public void update(Forum_reportVO report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer report_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Forum_reportVO findByPrimaryKey(Integer report_id) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
