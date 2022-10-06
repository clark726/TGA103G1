package com.manager.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ManagerVO implements Serializable {
    private Integer manager_id;
    private String account;
    private String password;
    private LocalDateTime lastLoginTime;
    private LocalDate birthday;
    private Integer status;
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
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    

}