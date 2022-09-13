package com.order.model;


import java.util.List;

public interface OrderVODAO {
    List<OrderVO> getAll();

    OrderVO get(Integer order_id);

    boolean add(OrderVO obj);

    boolean update(OrderVO obj);

    boolean delete(Integer order_id);
}
