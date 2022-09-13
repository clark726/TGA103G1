package com.store_theme.model;

import java.io.Serializable;

public class Store_themeVO implements Serializable {

	private Integer theme_id;
	private String theme_name;
	private String introduce;
	
	public Store_themeVO() {};
	
	public Store_themeVO(Integer theme_id , String theme_name , String introduce) {
		this.theme_id = theme_id;
		this.theme_name = theme_name;
		this.introduce = introduce;
	}
	
	
	public Integer getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
