package com.store_img.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.Result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store_img" , catalog = "barjarjo")
public class Store_imgVO extends Result implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer img_id;
	@Column(name = "store_id")
	private Integer storeId;
	@Column(insertable = false)
	private Date date;
	private String img;
	@Column(name = "status")
	private Integer status1;
	
	@Transient
	private String firstImg;
	@Transient
	private String secondImg;
	@Transient
	private String meanuImg;
	@Transient
	private String meanuImg2;
	@Transient
	private String imgstr;
	@Transient
	private byte[] ingbyte;
	@Transient
	private String account;
	@Transient
	private String dayoff;
	@Transient
	private String work_open;
	@Transient
	private String work_end;
	@Transient
	private String produce;
	@Transient
	private String name;
	
	


	public Store_imgVO(Integer img_id, Integer storeId, Date date, String img) {
		super();
		this.img_id = img_id;
		this.storeId = storeId;
		this.date = date;
		this.img = img;
	}


}
