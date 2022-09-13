package com.member_forum_img.model;

import java.sql.*;
import java.util.*;



public class Member_forum_imgVOJDBC implements Member_forum_imgVODAO {
    
	
	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	@Override
    public List<Member_forum_imgVO> getAll() {
        List<Member_forum_imgVO> member_forum_imgVOS = new ArrayList<>();
        String sql = "SELECT * FROM member_forum_img;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                member_forum_imgVOS.add(new Member_forum_imgVO(rs.getInt(1),
                        rs.getInt(2), rs.getBytes(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member_forum_imgVOS;
    }

    @Override
    public Member_forum_imgVO get(Integer member_forum_img_id) {
        Member_forum_imgVO member_forum_imgVO = new Member_forum_imgVO();
        String sql = "select * from member_forum_img where member_forum_img_id = ?;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,member_forum_img_id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                member_forum_imgVO.setMember_forum_img_id(rs.getInt(1));
                member_forum_imgVO.setForum_id(rs.getInt(2));
                member_forum_imgVO.setImg(rs.getBytes(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return member_forum_imgVO;
    }

    @Override
    public boolean add(Member_forum_imgVO obj) {
        int rows = -1;
        String sql = "INSERT INTO `member_forum_img` (`member_forum_img_id`,`forum_id`,`img`) VALUES (?,?,?);";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getMember_forum_img_id());
            ppst.setObject(2,obj.getForum_id());
            ppst.setObject(3,obj.getImg());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != -1;
    }

    @Override
    public boolean update(Member_forum_imgVO obj) {
        int rows = 0;
        String sql = "UPDATE `member_forum_img` SET `forum_id` = ?, `img` = ? WHERE (`member_forum_img_id` = ?);";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getForum_id());
            ppst.setObject(2,obj.getImg());
            ppst.setObject(3,obj.getMember_forum_img_id());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }

    @Override
    public boolean delete(Integer member_forum_img_id) {
        int rows = 0;
        String sql = "delete from member_forum_img where member_forum_img_id = ?;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,member_forum_img_id);
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }
}
