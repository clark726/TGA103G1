package com.product_img.model;

import java.io.Serializable;
import java.util.Date;

public class Product_imgVO implements Serializable {

	private Integer img_id;
	private Date date;
	private byte[] img;
	private Integer product_id;
	
	public Product_imgVO() {};
	
	
	public Product_imgVO(Integer img_id, Date date, byte[] img, Integer product_id) {
		super();
		this.img_id = img_id;
		this.date = date;
		this.img = img;
		this.product_id = product_id;
	}



	public Integer getImg_id() {
		return img_id;
	}
	
	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
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

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}


	@Override
	public String toString() {
		return "Product_imgVO [img_id=" + img_id + ", date=" + date + ", product_id=" + product_id + "]";
	}
	
	
	
}
