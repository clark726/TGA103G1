package com.store_img.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class Store_imgVO implements Serializable {

	private Integer img_id;
	private Integer store_id;
	private Date date;
	private String img;
	private String firstImg;
	private String secondImg;
	private String meanuImg;
	private String meanuImg2;
	private String imgstr;
	private Integer status1;
	private byte[] ingbyte;

	public byte[] getIngbyte() {
		return ingbyte;
	}

	public void setIngbyte(byte[] ingbyte) {
		this.ingbyte = ingbyte;
	}

	public Store_imgVO() {
	};

	public Store_imgVO(Integer img_id, Integer store_id, Date date, String img) {
		super();
		this.img_id = img_id;
		this.store_id = store_id;
		this.date = date;
		this.img = img;
	}

	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFirstImg() {
		return firstImg;
	}

	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}

	public String getSecondImg() {
		return secondImg;
	}

	public void setSecondImg(String secondImg) {
		this.secondImg = secondImg;
	}

	public String getMeanuImg() {
		return meanuImg;
	}

	public void setMeanuImg(String meanuImg) {
		this.meanuImg = meanuImg;
	}

	public String getMeanuImg2() {
		return meanuImg2;
	}

	public void setMeanuImg2(String meanuImg2) {
		this.meanuImg2 = meanuImg2;
	}

	public Integer getStatus1() {
		return status1;
	}

	public void setStatus1(Integer status1) {
		this.status1 = status1;
	}

	public String getImgstr() {
		return imgstr;
	}

	public void setImgstr(String imgstr) {
		this.imgstr = imgstr;
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



	@Override
	public String toString() {
		return "Store_imgVO [img_id=" + img_id + ", store_id=" + store_id + ", date=" + date + "]";
	}

}
