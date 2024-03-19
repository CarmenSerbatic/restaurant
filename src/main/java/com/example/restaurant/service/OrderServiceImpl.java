package com.example.restaurant.service;


import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;
import com.example.restaurant.data.repository.OrderRepository;

import com.example.restaurant.presentation.dto.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrderDay() {
        return orderRepository.findByDate(LocalDate.now());
    }

    @Override
    public List<Order> findOrderFinalized() {
        List<Order> orders = orderRepository.findByDate(LocalDate.now());
        List<Order> ordersFinalized = new ArrayList<Order>();
        for (Order o : orders){

            if (o.isFinalized()){
                ordersFinalized.add(o);
            }
        }
        return ordersFinalized;
    }

    @Override
    public List<Order> findOrderNotFinalized() {
        List<Order> orders = orderRepository.findByDate(LocalDate.now());
        List<Order> ordersNotFinalized = new ArrayList<Order>();
        for (Order o : orders){

            if (o.isFinalized() == false){
                ordersNotFinalized.add(o);
            }
        }
        return ordersNotFinalized;
    }

    @Override
    public List<Food> showOrderToKichen(Order order) {


        List<Food> foodes = new ArrayList<Food>();

        // Get order menus
        List<Menu>menus = order.getMenus();

        // Iterate over the menus in the order and count the occurrences of each menu object
        for (Menu menu : menus) {
            int cont = 0;
            for (Menu menucompair : menus) {
                if(menu.equals(menucompair)){
                    cont ++;
                }
            }
            Food food = new Food(menu.getDish(),cont);
            foodes.add(food);
        }

        return foodes;
    }

    @Override
    public Order findOrderById(long id) {
        Order order = null;
        Optional<Order> result = orderRepository.findById(id);

        if (result.isPresent()){
            order = result.get();
        }

        return order;
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrderById(long id) {

    }
}
