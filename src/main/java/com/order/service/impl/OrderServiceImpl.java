package com.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		
		
		//以storeId當成key做分類 
		Map<Integer,List<OrderSmallVO>> map = new HashMap();
		for(int x=0;x<list.size();x++) {
			OrderSmallVO orderSmallVO = list.get(x);
			if(map.containsKey(orderSmallVO.getStoreId())){
				ArrayList<OrderSmallVO> newlist2 = (ArrayList<OrderSmallVO>) map.get(orderSmallVO.getStoreId());
				newlist2.add(orderSmallVO);
			}else {
				List<OrderSmallVO> newlist = new ArrayList<>();
				newlist.add(orderSmallVO);
				map.put(orderSmallVO.getStoreId(), newlist);
			}
		}
		
		//計算價錢加總
		Map<Integer, Integer> mapPrice = new HashMap<Integer, Integer>();
			Iterator<Integer> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				Integer key = iter.next();
				List<OrderSmallVO> vo = map.get(key);
				for(int j = 0; j < vo.size(); j++) {
					if(mapPrice.containsKey(j)) {

					}else {
						mapPrice.put(j, vo.get(j).getAllPrice());
					}
				}
			}
			
		for(Integer key : map.keySet()) {
			obj.setStore_id(key);
//			obj.setPrice(key);
//			orderDao.insert(obj);
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
