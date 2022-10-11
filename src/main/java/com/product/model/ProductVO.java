package com.product.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.product_img.model.Product_imgVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", catalog = "barjarjo")
public class ProductVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	private String name;
	private Integer price;
	private Integer store_id;
	private String description;
	private Integer type_id;
	private Integer stock;
	@Column(insertable = false)
	private Integer status;
	@Column(insertable = false)
	private Date date;
	@Transient
	private List<Object> imgs;
	@Transient
	private Object img;
	@OneToMany
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private List<Product_imgVO> product_imgVO;
	
}
