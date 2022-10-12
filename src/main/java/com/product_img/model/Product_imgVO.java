package com.product_img.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.product.model.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_img", catalog = "barjarjo")
public class Product_imgVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer img_id;
	@Column(insertable = false)
	private Date date;
	private byte[] img;
	private Integer product_id;
	@Transient
	private Object imgobj;
	

	public Product_imgVO(Integer img_id, Date date, byte[] img, Integer product_id) {
		super();
		this.img_id = img_id;
		this.date = date;
		this.img = img;
		this.product_id = product_id;
	}

	public Product_imgVO(Object imgobj, Integer product_id) {
		this.imgobj = imgobj;
		this.product_id = product_id;
	}

}
