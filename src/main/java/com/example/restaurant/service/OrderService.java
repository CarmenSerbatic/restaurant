package com.example.restaurant.service;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;
import com.example.restaurant.presentation.dto.Food;

import java.util.List;

public interface OrderService {

    public List<Order> findAllOrder();
    public List<Order> findOrderDay();

    public List<Order> findOrderFinalized();
    public List<Order> findOrderNotFinalized();
    public Order findOrderById(long id);

    public List<Food> showOrderToKitchen(Order order);

    public Order addOrder(Order order);
    public void updateOrder(Order order);
    public void deleteOrderById(long id);
}
