package com.like.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeVOJDBC implements LikeDAO {
    public static final String URL="jdbc:mysql://localhost:3306/barjarjo?Timezone=Asia/Taipei",USER="root",PASSWORD="password";

    @Override
    public List<LikeVO> getAll() {
        List<LikeVO> likeVO = new ArrayList<>();
        String sql = "SELECT * FROM `like`;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                likeVO.add(new LikeVO(rs.getInt(1),rs.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return likeVO;
    }

    @Override
    public LikeVO get(Integer forum_id,Integer member_id) {
        LikeVO likeVO = null;
        String sql = "select * from `like` where forum_id = ? and member_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, forum_id);
            ps.setObject(2,member_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                likeVO = new LikeVO(rs.getInt(1),rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return likeVO;
    }

    @Override
    public boolean add(LikeVO obj) {
        int rowCount = 0;
        String sql = "INSERT INTO `like` (`forum_id`, `member_id`) VALUES (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1,obj.getForum_id());
            ps.setObject(2,obj.getMember_id());
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }

    @Override
    public boolean update(LikeVO oldObj,LikeVO newObj) {
        int rowCount = 0;
        String sql = "UPDATE `like` SET `forum_id` = ? , `member_id` = ? WHERE (`forum_id` = ?) and (`member_id` = ?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
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

    @Override
    public boolean delete(Integer forum_id,Integer member_id) {
        int rowCount = 0;
        String sql = "delete from `like` where forum_id = ? and member_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1,forum_id);
            ps.setObject(2,member_id);
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount != 0;
    }
}
