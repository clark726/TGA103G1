package com.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerJNDIDAO implements ManagerDAO{
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
    public List<ManagerVO> getAll() {
        List<ManagerVO> managerVOS = new ArrayList<>();
        String sql = "SELECT * FROM manager;";
        try(Connection conn = ds.getConnection();
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
        try(Connection conn = ds.getConnection();
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
        int rows = 0;
        String sql = "INSERT INTO `manager` (`account`, `password`) VALUES (?,?);";
        try(PreparedStatement ppst = ds.getConnection().prepareStatement(sql)){
            ppst.setObject(1,obj.getAccount());
            ppst.setObject(2,obj.getPassword());
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows == 1;
    }

    @Override
    public boolean update(ManagerVO obj) {
        int rows = 0;
        String sql = "UPDATE `manager` SET `account` = ?, `password` = ? WHERE (`manager_id` = ?);";
        try(Connection conn = ds.getConnection();
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
        try(Connection conn = ds.getConnection();
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,manager_id);
            rows = ppst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows == 1;
    }
    public boolean check(String account,String password) {
    	String sql = "select count(2) from manager where account=? and password= ?;";
        try(Connection conn = ds.getConnection();
            PreparedStatement ppst = conn.prepareStatement(sql)){
            ppst.setObject(1,account);
            ppst.setObject(2, password);
            ResultSet rs = ppst.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1) == 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    	return false;
    }
}
