package com.example.restaurant.service;



import com.example.restaurant.data.entity.Order;
import com.example.restaurant.data.entity.OrderMenuRelation;
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
    public Order findOrderById(long id) {

        Order order = null;

        Optional<Order> result = orderRepository.findById(id);

        if (result.isPresent()){
            order = result.get();
        }

        return order;
    }


    @Override
    public List<Order> findOrderDay() {
        return orderRepository.findByDate(LocalDate.now());
    }

    @Override
    public List<Order> findOrderFinalized() {

        List<Order> orders = orderRepository.findByFinalizedAndDate(true , LocalDate.now());

        return orders;
    }


    @Override
    public List<Food> showOrderToKitchen(Order order) {


        List<Food> foodes = new ArrayList<Food>();

        // Get List of OrdenMenurelation by this order
        List<OrderMenuRelation> relations = order.getOrderMenuRelations();

        // Iterate over the orders and save values of dish and amount in the food object.
        for (OrderMenuRelation o : relations) {

            //save data we need in the dto Food and save in foodes
            Food food = new Food(o.getMenu().getDish(),o.getQuantity());

            foodes.add(food);
        }

        return foodes;
    }



    @Override
    public Order addOrder(Order order) {

         /*                    -------IMPORTANT----
         You must first save the relations objects before saving the order so that the relationship
         between both is created correctly
         */

        // Get List of OrdenMenurelation by this order
        List<OrderMenuRelation> relations = order.getOrderMenuRelations();

        // Iterates the relations list and saves each object
        for (OrderMenuRelation o : relations) {
            o.setOrder(order);
        }

        //Save the new order
        return orderRepository.save(order);

    }

    @Override
    public Order changeOrderToFinished(Order order) {

        //change finalized to true
        order.setFinalized(true);

        //Save order, overwrite object
        return orderRepository.save(order);
    }

//    @Override
//    public void deleteOrderById(long id) {
//
//    }
}
