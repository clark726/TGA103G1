package com.order.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.order.model.OrderDAO;
import com.order.model.OrderJNDI;
import com.order.model.OrderSmallVO;
import com.order.model.OrderVO;
import com.order.service.OrderService;
import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailJNDI;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;
import net.bytebuddy.asm.Advice.Return;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDao;
	private Order_detailDAO order_detailDAO;
	public static AllInOne allInOne;

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
		// 搜尋沒有值
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
	public OrderVO insert(OrderVO obj) throws UnsupportedEncodingException {
		List<OrderSmallVO> list = obj.getOrderSmallVO();

		// 以storeId當成key做分類
		Map<Integer, List<OrderSmallVO>> map = new HashMap();
//		for (int x = 0; x < list.size(); x++) {
//			OrderSmallVO orderSmallVO = list.get(x);
//			if (map.containsKey(orderSmallVO.getStoreId())) {
//				ArrayList<OrderSmallVO> newlist2 = (ArrayList<OrderSmallVO>) map.get(orderSmallVO.getStoreId());
//				newlist2.add(orderSmallVO);
//			} else {
//				List<OrderSmallVO> newlist = new ArrayList<>();
//				newlist.add(orderSmallVO);
//				map.put(orderSmallVO.getStoreId(), newlist);
//			}
//		}
		// 以stream API方式
		map = list.stream().collect(Collectors.groupingBy(OrderSmallVO::getStoreId));

		// 算出商品總和放到第一個商品的allprice
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
			orderId = orderDao.insert(obj);
			for (int i = 0; i < entry.getValue().size(); i++) {
				Integer amount = entry.getValue().get(i).getCount();
				Integer productId = entry.getValue().get(i).getProductId();
				order_detailDAO.insert(orderId, productId, amount);
			}
		}
//		----------------------------綠界------------------------------------
		List<OrderSmallVO> orderSmallVOs2 = obj.getOrderSmallVO();
		// 綠界取出商品名稱
		List<String> productName = orderSmallVOs2.stream().map(e->e.getName()).collect(Collectors.toList());
		// 為了配合綠界
		Optional<String> reduce = productName.stream().reduce((String acc, String curr) -> {
			return acc + "#" + curr;
		});
		String name = reduce.get();
		// 時間
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmm");
		String orderDate = sdf.format(new Date());
		String orderDate2 = sdf2.format(new Date());
		allInOne = new AllInOne("");
		AioCheckOutALL aCheckOut = new AioCheckOutALL();
		aCheckOut.setMerchantTradeNo(orderDate2 + "tga103");
		aCheckOut.setMerchantTradeDate(orderDate);
		aCheckOut.setTotalAmount(obj.getPrice() + "");
		aCheckOut.setTradeDesc("test");
		aCheckOut.setItemName(name);
		aCheckOut.setClientBackURL("http://localhost:8080/TGA103G1/main.html");
		aCheckOut.setReturnURL("http://localhost:8080/TGA103G1/main.html");
		aCheckOut.setNeedExtraPaidInfo("N");

		String checkValue = EcpayFunction.genCheckMacValue("pwFHCqoQZGmho4w6", "EkRm7iFT261dpevs", aCheckOut);
		obj.setAio(aCheckOut);
		obj.setMessage(checkValue);
		return obj;
	}

	@Override
	public boolean update(OrderVO obj) {
		return false;
	}

	@Override
	public boolean delete(Integer order_id) {
		return false;
	}

	@Override
	public OrderVO ecPay(OrderVO orderVO) throws UnsupportedEncodingException {
		List<OrderSmallVO> orderSmallVOs2 = orderVO.getOrderSmallVO();
		// 綠界取出商品名稱
		List<String> productName = orderSmallVOs2.stream().map(e->e.getName()).collect(Collectors.toList());
		// 為了配合綠界
		Optional<String> reduce = productName.stream().reduce((String acc, String curr) -> {
			return acc + "#" + curr;
		});
		String name = reduce.get();
		// 時間
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderDate = sdf.format(new Date());
		allInOne = new AllInOne("");
		AioCheckOutALL aCheckOut = new AioCheckOutALL();
		aCheckOut.setMerchantTradeNo(orderDate + "tga103");
		aCheckOut.setMerchantTradeDate(orderDate);
		aCheckOut.setTotalAmount(orderVO.getPrice() + "");
		aCheckOut.setTradeDesc("test");
		aCheckOut.setItemName(name);
		aCheckOut.setClientBackURL("http://localhost:8080/TGA103G1/main.html");
		aCheckOut.setReturnURL("http://localhost:8080/TGA103G1/main.html");
		aCheckOut.setNeedExtraPaidInfo("N");

		String checkValue = EcpayFunction.genCheckMacValue("pwFHCqoQZGmho4w6", "EkRm7iFT261dpevs", aCheckOut);
		orderVO.setAio(aCheckOut);
		orderVO.setMessage(checkValue);
		return orderVO;
	}

}
