package com.order.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class OrderVOJDBC implements OrderVODAO{
	
	String url = "jdbc:mysql://localhost:3306/barjarjo?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
    @Override
    public List<OrderVO> getAll() {
        List<OrderVO> orderVOS = new ArrayList<>();
        String sql = "SELECT * FROM `order`;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                orderVOS.add(new OrderVO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getObject(5, LocalDateTime.class),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderVOS;
    }

    @Override
    public OrderVO get(Integer order_id) {
        OrderVO orderVO = new OrderVO();
        String sql = "select * from `order` where `order_id` = ?;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,order_id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                orderVO.setOrder_id(rs.getInt(1));
                orderVO.setStore_id(rs.getInt(2));
                orderVO.setMember_id(rs.getInt(3));
                orderVO.setPrice(rs.getInt(4));
                orderVO.setDate(rs.getObject(5,LocalDateTime.class));
                orderVO.setMethod(rs.getInt(6));
                orderVO.setStatus(rs.getInt(7));
                orderVO.setName(rs.getString(8));
                orderVO.setAddress(rs.getString(9));
                orderVO.setPhone(rs.getString(10));
                orderVO.setNote(rs.getString(11));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderVO;
    }

    @Override
    public boolean add(OrderVO obj) {
        int rows = -1;
        String sql = "INSERT INTO `order` (`store_id`, `member_id`, `price`, `method`, `name`, `address`, `phone`,`note`) VALUES (?, ?, ?, ?, ?, ?, ?,?);";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getStore_id());
            ppst.setObject(2,obj.getMember_id());
            ppst.setObject(3,obj.getPrice());
            ppst.setObject(4,obj.getMethod());
            ppst.setObject(5,obj.getName());
            ppst.setObject(6,obj.getAddress());
            ppst.setObject(7,obj.getPhone());
            ppst.setObject(8,obj.getNote());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != -1;
    }

    @Override
    public boolean update(OrderVO obj) {
        int rows = 0;
        String sql = "UPDATE `order` set `store_id`=?,`member_id`=?,`price`=?,`method`=?,`name`=?,`address`=?,`phone`=?,`note`=? where `order_id`=?;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getStore_id());
            ppst.setObject(2,obj.getMember_id());
            ppst.setObject(3,obj.getPrice());
            ppst.setObject(4,obj.getMethod());
            ppst.setObject(5,obj.getName());
            ppst.setObject(6,obj.getAddress());
            ppst.setObject(7,obj.getPhone());
            ppst.setObject(8,obj.getNote());
            ppst.setObject(9,obj.getOrder_id());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }

    @Override
    public boolean delete(Integer order_id) {
        int rows = 0;
        String sql = "delete from member_forum_img where member_forum_img_id = ?;";
        try (Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement ppst = conn.prepareStatement("delete from `order_detail` where order_id = 1;");
            PreparedStatement ppst2 = conn.prepareStatement(sql)){
            ppst.setObject(1,order_id);
            ppst.executeUpdate();
            rows = ppst2.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }
}
