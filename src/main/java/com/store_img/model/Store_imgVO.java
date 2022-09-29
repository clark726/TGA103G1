package com.store_img.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

import com.common.Result;

public class Store_imgVO extends Result implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	private String account;
	private String dayoff;
	private String work_open;
	private String work_end;
	private String produce;
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

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
