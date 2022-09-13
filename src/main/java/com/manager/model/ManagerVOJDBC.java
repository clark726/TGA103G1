package com.manager.model;

import java.sql.*;
import java.util.*;

public class ManagerVOJDBC implements ManagerDAO{
	String URL = "jdbc:mysql://localhost:3306/barjarjo?serverTimezone=Asia/Taipei";
	String USER = "root";
	String PASSWORD = "password";
    @Override
    public List<ManagerVO> getAll() {
        List<ManagerVO> managerVOS = new ArrayList<>();
        String sql = "SELECT * FROM manager;";
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                managerVOS.add(new ManagerVO(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return managerVOS;
    }

    @Override
    public ManagerVO get(Integer manager_id) {
        ManagerVO managerVO = new ManagerVO();
        String sql = "select * from manager where manager_id = ?;";
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,manager_id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()){
                managerVO.setManager_id(rs.getInt(1));
                managerVO.setAccount(rs.getString(2));
                managerVO.setPassword(rs.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return managerVO;
    }

    @Override
    public boolean add(ManagerVO obj) {
        int rows = -1;
        String sql = "INSERT INTO `manager` (`account`, `password`) VALUES (?,?);";
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getAccount());
            ppst.setObject(2,obj.getPassword());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != -1;
    }

    @Override
    public boolean update(ManagerVO obj) {
        int rows = 0;
        String sql = "UPDATE `manager` SET `account` = ?, `password` = ? WHERE (`manager_id` = ?);";
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,obj.getAccount());
            ppst.setObject(2,obj.getPassword());
            ppst.setObject(3,obj.getManager_id());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }

    @Override
    public boolean delete(Integer manager_id) {
        int rows = 0;
        String sql = "delete from manager where manager_id = ?;";
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,manager_id);
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows != 0;
    }
}