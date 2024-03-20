package com.example.restaurant.service;



import com.example.restaurant.data.entity.Order;
import com.example.restaurant.data.entity.OrderMenuRelation;
import com.example.restaurant.data.repository.OrderMenuRelationRepository;
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

    @Autowired
    private OrderMenuRelationRepository orderMenuRelationRepository;
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
    public List<Food> showOrderToKitchen(Order order) {


        List<Food> foodes = new ArrayList<Food>();

        // Get List of OrdenMenurelation by this order
        List<OrderMenuRelation> relations = order.getOrderMenuRelations();

        // Iterate over the menus in the order and count the occurrences of each menu object
        for (OrderMenuRelation o : relations) {

            //save data we need in the dto Food and save in foodes
            Food food = new Food(o.getMenu().getDish(),o.getQuantity());

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
    public Order addOrder(Order order) {
        List<OrderMenuRelation> relations = order.getOrderMenuRelations();
        for (OrderMenuRelation o : relations) {
            o.setOrder(order);

//           if(orderMenuRelationRepository.findById(o.getId_relation()) == null){
//
//               orderMenuRelationRepository.save(o);
//           }
        }
        return orderRepository.save(order);

//        for (OrderMenuRelation o : relations) {
//
//           if(orderMenuRelationRepository.findById(o.getId_relation()) == null){
//
//               orderMenuRelationRepository.save(o);
//           }
//        }


    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrderById(long id) {

    }
}
