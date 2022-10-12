package com.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.order.model.OrderDAO;
import com.order.model.OrderJNDI;
import com.order.model.OrderSmallVO;
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
		List<OrderSmallVO> list = obj.getOrderSmallVO();

		// 以storeId當成key做分類
		Map<Integer, List<OrderSmallVO>> map = new HashMap();
		for (int x = 0; x < list.size(); x++) {
			OrderSmallVO orderSmallVO = list.get(x);
			if (map.containsKey(orderSmallVO.getStoreId())) {
				ArrayList<OrderSmallVO> newlist2 = (ArrayList<OrderSmallVO>) map.get(orderSmallVO.getStoreId());
				newlist2.add(orderSmallVO);
			} else {
				List<OrderSmallVO> newlist = new ArrayList<>();
				newlist.add(orderSmallVO);
				map.put(orderSmallVO.getStoreId(), newlist);
			}
		}

		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
			int total = 0;
			for (int x = 0; x < entry.getValue().size(); x++) {
				total += entry.getValue().get(x).getAllPrice();
				if (x == entry.getValue().size() - 1) {
					entry.getValue().get(0).setAllPrice(total);
				}
			}
		}

		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
			obj.setStore_id(entry.getKey());
			obj.setPrice(entry.getValue().get(0).getAllPrice());

		}

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
