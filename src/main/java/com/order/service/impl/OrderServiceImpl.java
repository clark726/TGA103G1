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
	public OrderVO getOrderByOrderId(OrderVO orderVO) {
		String account = orderVO.getAccount();
		Integer order_id = orderVO.getOrder_id();
//搜尋沒有值		
		OrderVO backOrderVO = orderDao.getOrderByOrderId(account, order_id);
		
		if (backOrderVO.getMember_id() == null) {
			orderVO.setSuccessful(false);
			orderVO.setMessage("沒有此訂單");
			return orderVO;
		} else {
			backOrderVO.setSuccessful(true);
			return backOrderVO;
		}
		
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
