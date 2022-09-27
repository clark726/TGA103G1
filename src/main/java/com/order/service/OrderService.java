package com.order.service;

import java.util.List;

import com.order.model.OrderVO;

public interface OrderService {

	public List<OrderVO> getAllByStoreAccount(String account);

	public OrderVO getOrderByOrderId(String account , Integer order_id);

	public List<OrderVO> getOrderBySataus(String account ,Integer status);
	
	public boolean insert(OrderVO obj);

	public boolean update(OrderVO obj);

	public boolean delete(Integer order_id);
}
