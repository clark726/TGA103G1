package com.order.service.impl;

import java.util.List;

import com.order.model.OrderDAO;
import com.order.model.OrderJNDI;
import com.order.model.OrderVO;
import com.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDao;

	public OrderServiceImpl() {

		orderDao = new OrderJNDI();
	}

	@Override
	public List<OrderVO> getAllByStoreAccount(String account) {
		return orderDao.getAllByStoreAccount(account);
	}

	@Override
	public OrderVO getOrderByOrderId(String account , Integer order_id) {
		return orderDao.getOrderByOrderId(account, order_id);
	}
	
	@Override
	public List<OrderVO> getOrderBySataus(String account, Integer status) {
		return orderDao.getOrderBySataus(account, status);
	}

	
	

	@Override
	public boolean insert(OrderVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(OrderVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer order_id) {
		// TODO Auto-generated method stub
		return false;
	}



}
