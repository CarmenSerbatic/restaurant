package com.example.restaurant.presentation.controller;


import com.example.restaurant.data.entity.Order;

import com.example.restaurant.data.entity.OrderMenuRelation;
import com.example.restaurant.presentation.dto.Food;
import com.example.restaurant.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * List all orders
     * http://localhost:8081/order/list
     * @return List<Order>
     */
    @GetMapping("/list")
    public List<Order> findAll(){
        return orderService.findAllOrder();
    }

    @GetMapping("/{id}")
    @Operation(description = "Find order by id")
    public ResponseEntity<Order> findOneById(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        if(order != null){
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/day")
    @Operation(description = "Find orders by date")
    public List<Order> findByDate(){

        List<Order> orders = orderService.findOrderDay();

        return orders;
    }


    @GetMapping("/kitchen/{id}")
    @Operation(description = "")
    public ResponseEntity<List<Food>> showOrderToKitchen(@PathVariable Long id){

        Order order = orderService.findOrderById(id);

        if ( order != null) {
            return ResponseEntity.ok(orderService.showOrderToKitchen(order));
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/finalized")
    @Operation(description = "Find orders by finalized is true")
    public List<Order> findOrderByFinalized(){

        List<Order> orders = orderService.findOrderFinalized();

        return orders;
    }

    @PostMapping("/")
    public ResponseEntity<Order> create(@RequestBody Order order){

        if(order != null){
            // If exist id, cannot create new menu
            if(orderService.findOrderById(order.getId_order()) != null){
                log.warn("trying to create a order with id");
                return ResponseEntity.badRequest().build();
            }

            //Create new order
            order = orderService.addOrder(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/finalized")
    public ResponseEntity<Order> updateFinalized(@RequestBody Order order){

        if(order != null){
            // If exist id, cannot create new menu
            if(orderService.findOrderById(order.getId_order()) != null){
                log.warn("trying to create a order with id");
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.notFound().build();
            }

            //Create new order
//            orderService.addOrder(order);
//            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
