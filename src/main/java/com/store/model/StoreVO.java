package com.store.model;

import java.io.Serializable;

public class StoreVO implements Serializable {

	private Integer store_id;
	private String account;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String lng;
	private String lat;
	private Integer theme_id;
	private String dayoff;
	private String work_open;
	private String work_end;
	private String produce;
	
	public StoreVO() {};
	
	public StoreVO(Integer store_id, String account, String name, String password, String phone, String email,
			String address, String lng, String lat, Integer theme_id, String dayoff, String work_open,
			String work_end , String produce) {
		super();
		this.store_id = store_id;
		this.account = account;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.theme_id = theme_id;
		this.dayoff = dayoff;
		this.work_open = work_open;
		this.work_end = work_end;
		this.produce = produce;
	}


	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	public String getDayoff() {
		return dayoff;
	}

	public void setDayoff(String dayoff) {
		this.dayoff = dayoff;
	}

	public String getWork_open() {
		return work_open;
	}

	public void setWork_open(String work_open) {
		this.work_open = work_open;
	}

	public String getWork_end() {
		return work_end;
	}

	public void setWork_end(String work_end) {
		this.work_end = work_end;
	}

	public void setstore_id(Integer store_id) {
		this.store_id = store_id;
 	}
	public Integer getstore_id() {
		return  store_id;
	}
	public String getProduce() {
		return produce;
	}
	public void setProduce(String produce) {
		this.produce = produce;
	}

	@Override
	public String toString() {
		return "StoreVO [store_id=" + store_id + ", account=" + account + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", lng=" + lng + ", lat=" + lat
				+ ", theme_id=" + theme_id + ", dayoff=" + dayoff + ", work_open=" + work_open + ", work_end="
				+ work_end + ", produce=" + produce + "]";
	}
	
	


}

 
