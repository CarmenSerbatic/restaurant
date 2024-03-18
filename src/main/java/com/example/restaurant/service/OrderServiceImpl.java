package com.example.restaurant.service;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;
import com.example.restaurant.data.repository.OrderRepository;
import com.example.restaurant.presentation.dto.Dish;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

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
        List<Order> ordersFinalized = null;
        for (Order o : orders){
            System.out.println(o);
            if (o.isFinalized()){
                ordersFinalized.add(o);
            }
        }
        return ordersFinalized;
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
