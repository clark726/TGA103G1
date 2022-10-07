package com.forum.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.forum_message.model.Forum_messageVO;
import com.member.vo.MemberVO;

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
	public List<ForumVO> getAll() {
        List<ForumVO> forumVOs = new ArrayList<>();
        String sql = "SELECT * FROM barjarjo.forum order by `date` desc;";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                forumVOs.add(new ForumVO(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getObject(4, LocalDateTime.class)
                        ,rs.getString(5), rs.getInt(6),rs.getInt(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return forumVOs;
    }
	
	public List<Map<String,Object>> getAllByForumMessage(Integer forum_id) {
        List<Map<String,Object>> messages = new ArrayList<>();
        String sql = "SELECT fm.member_id,fm.content,fm.`date`,m.account,m.gender,m.email from forum f "
        		+ "join forum_message fm on f.forum_id = fm.forum_id "
        		+ "join `member` m on fm.member_id=m.member_id "
        		+ "where f.forum_id = ?;";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
        	ppst.setObject(1,forum_id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
            	Map<String,Object> map = new HashMap<>();
            	map.put("memberId", rs.getInt(1));
            	map.put("content", rs.getString(2));
            	map.put("date", rs.getObject(3,LocalDateTime.class));
            	map.put("account",rs.getString(4));
            	map.put("gender",rs.getInt(5));
            	map.put("email",rs.getString(6));
            	messages.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public ForumVO get(Integer forum_id) {
        ForumVO forumVO = new ForumVO();
        String sql = "select * from forum where forum_id = ?;";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,forum_id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                forumVO.setForum_id(rs.getInt(1));
                forumVO.setMember_id(rs.getInt(2));
                forumVO.setContent(rs.getString(3));
                forumVO.setDate(rs.getObject(4,LocalDateTime.class));
                forumVO.setTitle(rs.getString(5));
                forumVO.setLike(rs.getInt(6));
                forumVO.setLook(rs.getInt(7));
                forumVO.setMessage(rs.getInt(8));
                forumVO.setStatus(rs.getInt(9));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return forumVO;
    }

    public boolean add(ForumVO obj) {
        int rows = 0;
        String sql = "INSERT INTO `forum` (`member_id`, `content`, `title`) VALUES (?,?,?);";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,obj.getMember_id());
            ppst.setObject(2,obj.getContent());
            ppst.setObject(3,obj.getTitle());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows == 1;
    }
    public MemberVO findMemberByForumId(Integer forumId) {
        String sql = "SELECT `account` , gender,email from member join forum on member.member_id = forum.member_id\r\n"
        		+ "where forum_id = ?;";
        MemberVO memberVO = null;
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,forumId);
            ResultSet resultSet = ppst.executeQuery();
            while (resultSet.next()) {
				memberVO = new MemberVO();
				memberVO.setAccount(resultSet.getString(1));
				memberVO.setGender(resultSet.getInt(2));
				memberVO.setEmail(resultSet.getString(3));
			}
        }catch (Exception e){
            e.printStackTrace();
        }
        return memberVO;
    }
    
    
    public boolean activation(Integer forumId,Integer status) {
    	int row = 0 ;
    	String sql ="UPDATE `forum` SET `status` = ? WHERE `forum_id` = ?;";
    	try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,status);
            ppst.setObject(2,forumId);
            row = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row == 1;
    }
    
    public boolean blockade(Integer forumId) {
    	int row = 0 ;
    	String sql ="UPDATE `forum` SET `status` = 0 WHERE `forum_id` = ?;";
    	try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,forumId);
            row = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row == 1;
    }

    public boolean update(ForumVO obj) {
        int rows = 0;
        String sql = "UPDATE `forum` SET `content` = ? WHERE (`forum_id` = ?);";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,obj.getContent());
            ppst.setObject(2,obj.getForum_id());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows == 1;
    }

    public boolean delete(Integer id) {
        int rows = 0;
        String sql = "delete  from `forum` where forum_id = ?;";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,id);
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows == 1;
    }

}
