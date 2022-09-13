package com.manager.model;
import java.io.Serializable;

public class ManagerVO implements Serializable {
    private Integer manager_id;
    private String account;
    private String password;

    public ManagerVO() {
    }

    public ManagerVO(Integer manager_id, String account, String password) {
        this.manager_id = manager_id;
        this.account = account;
        this.password = password;
    }

    public ManagerVO(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagerVO{" +
                "manager_id=" + manager_id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}