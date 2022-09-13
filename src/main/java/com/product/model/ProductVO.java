package com.product.model;

import java.io.Serializable;
import java.sql.Date;

public class ProductVO implements Serializable {

	private Integer product_id;
	private String name;
	private Integer price;
	private Integer store_id;
	private String description;
	private Integer type_id;
	private Integer stock;
	private Integer status;
	private Date date;
	
	public ProductVO() {};
	
	
	
	public ProductVO(Integer product_id, String name, Integer price, Integer store_id, String description,
			Integer type_id, Integer stock, Integer status, Date date) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.store_id = store_id;
		this.description = description;
		this.type_id = type_id;
		this.stock = stock;
		this.status = status;
		this.date = date;
	}




	public Integer getProduct_id(){
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", name=" + name + ", price=" + price + ", store_id=" + store_id
				+ ", description=" + description + ", type_id=" + type_id + ", stock=" + stock + ", status=" + status
				+ ", date=" + date + "]";
	}

	
}
