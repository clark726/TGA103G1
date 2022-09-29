package com.order_detail.model;

import java.util.List;

public interface Order_detailDAO {

	public boolean insert(Order_detailVO obj);

	public boolean update(Order_detailVO obj);

	public boolean delete(Integer order_id, Integer product_id);

	public Order_detailVO findByPrimaryKey(Integer id);

	public List<Order_detailVO> getAllByOrderId(Integer order_id);
	
	public void updateStatus(Integer order_id , Integer status);

}
