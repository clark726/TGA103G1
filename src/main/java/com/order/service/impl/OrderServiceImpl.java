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
import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailJNDI;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDao;
	private Order_detailDAO order_detailDAO;
	public OrderServiceImpl() {

		orderDao = new OrderJNDI();
		order_detailDAO = new Order_detailJNDI();
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
		//算出商品總和放到第一個商品的allprice
		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
			int total = 0;
			for (int x = 0; x < entry.getValue().size(); x++) {
				total += entry.getValue().get(x).getAllPrice();
				if (x == entry.getValue().size() - 1) {
					entry.getValue().get(0).setAllPrice(total);
				}
			}
		}
		Integer orderId = null;
		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
			obj.setStore_id(entry.getKey());
			obj.setPrice(entry.getValue().get(0).getAllPrice());
			 orderId =  orderDao.insert(obj);
			for(int i = 0; i < entry.getValue().size(); i++) {
			Integer amount = entry.getValue().get(i).getCount();
			Integer productId = entry.getValue().get(i).getProductId();
				order_detailDAO.insert(orderId , productId , amount);
			}
		}
		return orderId != 0;
	}

	@Override
	public boolean update(OrderVO obj) {
		return false;
	}

	@Override
	public boolean delete(Integer order_id) {
		return false;
	}

}
