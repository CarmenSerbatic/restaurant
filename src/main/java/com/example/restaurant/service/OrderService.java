package com.example.restaurant.service;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAllOrder();
    public List<Order> findOrderDay();

    public List<Order> findOrderFinalized();

    public Order findOrderById(long id);

    public void addOrder(Order order);
    public void updateOrder(Order order);
    public void deleteOrderById(long id);
}
