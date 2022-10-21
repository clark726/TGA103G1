package com.like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LikeVOJNDI implements LikeDAO {
    private static DataSource ds = null;
    
    static {
        try {
             Context ctx = new InitialContext();
             ds = (DataSource) ctx.lookup("java:comp/env/jdbc/barjarjo");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    public List<LikeVO> getAll() {
        List<LikeVO> likeVO = new ArrayList<>();
        String sql = "SELECT * FROM `like`;";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)
        		) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                likeVO.add(new LikeVO(rs.getInt(1),rs.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return likeVO;
    }
    public LikeVO get(Integer forum_id,Integer member_id) {
        LikeVO likeVO = new LikeVO();
        String sql = "select * from `like` where forum_id = ? and member_id = ?;";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, forum_id);
            ps.setObject(2,member_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                likeVO = new LikeVO(rs.getInt(1),rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return likeVO;
    }
    
    public boolean add(LikeVO obj) {
        int rowCount = 0;
        String sql = "INSERT INTO `like` (`forum_id`, `member_id`) VALUES (?,?);";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,obj.getForum_id());
            ps.setObject(2,obj.getMember_id());
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }

    public boolean update(LikeVO oldObj,LikeVO newObj) {
        int rowCount = 0;
        String sql = "UPDATE `like` SET `forum_id` = ? , `member_id` = ? WHERE (`forum_id` = ?) and (`member_id` = ?);";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,newObj.getForum_id());
            ps.setObject(2,newObj.getMember_id());
            ps.setObject(3,oldObj.getForum_id());
            ps.setObject(4,oldObj.getMember_id());
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }

    public boolean delete(LikeVO obj) {
        int rowCount = 0;
        String sql = "delete from `like` where forum_id = ? and member_id = ?;";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,obj.getForum_id());
            ps.setObject(2,obj.getMember_id());
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }
    
    public boolean addFourmLike(LikeVO obj) {
        int rowCount = 0;
        String sql = " Update forum set `like`=`like`+1 where forum_id= ?;";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,obj.getForum_id());
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }
    
    public boolean deleteFourmLike(LikeVO obj) {
        int rowCount = 0;
        String sql = " Update forum set `like`=`like`-1 where forum_id= ?;";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,obj.getForum_id());
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }
}
