package com.order_detail.model;

import java.io.Serializable;

import com.common.Result;

public class Order_detailVO extends Result implements Serializable {
    private Integer order_id;
    private Integer product_id;
    private Integer amount;
    private String product_name;
    private Integer status;
  
   
	public Order_detailVO() {
    	
    }
    
	
    public Order_detailVO(Integer order_id, Integer product_id, Integer amount) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.amount = amount;
	}

    
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}



