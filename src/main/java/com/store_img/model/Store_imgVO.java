package com.store_img.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class Store_imgVO implements Serializable{

	private Integer img_id;
	private Integer store_id;
	private Date date;
	private byte[] img;
	
	public Store_imgVO() {};
	
	public Store_imgVO(Integer img_id ,Integer store_id, Date date, byte[] img) {
		super();
		this.img_id = img_id;
		this.store_id = store_id;
		this.date = date;
		this.img = img;
	}
	
	public Integer getImg_id() {
		return img_id;
	}
	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
	}
	
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Store_imgVO [img_id=" + img_id + ", store_id=" + store_id + ", date=" + date + "]";
	}
	
	
}
