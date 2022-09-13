package com.forum.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ForumJDBC implements ForumDAO{
  
    
	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
    
    public List<ForumVO> getAll() {
        List<ForumVO> forumVOs = new ArrayList<>();
        String sql = "SELECT * FROM forum;";
        try(Connection conn = DriverManager.getConnection(url,userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
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

    @Override
    public ForumVO get(Integer forum_id) {
        ForumVO forumVO = new ForumVO();
        String sql = "select * from forum where forum_id = ?;";
        try(Connection conn = DriverManager.getConnection(url,userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
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

    @Override
    public boolean add(ForumVO obj) {
        int rows = -1;
        String sql = "INSERT INTO `forum` (`member_id`, `content`, `title`) VALUES (?,?,?);";
        try(Connection conn = DriverManager.getConnection(url,userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getMember_id());
            ppst.setObject(2,obj.getContent());
            ppst.setObject(3,obj.getTitle());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != -1;
    }

    @Override
    public boolean update(ForumVO obj) {
        int rows = 0;
        String sql = "UPDATE `forum` SET `content` = ? WHERE (`forum_id` = ?);";
        try(Connection conn = DriverManager.getConnection(url,userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getContent());
            ppst.setObject(2,obj.getForum_id());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }

    @Override
    public boolean delete(Integer id) {
        int rows = 0;
        String sql = "delete  from `forum` where forum_id = ?;";
        try(Connection conn = DriverManager.getConnection(url,userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,id);
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }
}
