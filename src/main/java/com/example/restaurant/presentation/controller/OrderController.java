package com.example.restaurant.presentation.controller;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;
import com.example.restaurant.presentation.dto.Dish;
;import com.example.restaurant.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
//        List<Dish> dishes = new ArrayList<Dish>();
//        for (Menu m : menus){
//            System.out.println(m);
//            Dish dish = new Dish(m.getDish(), m.getPrice());
//            dishes.add(dish);
//        }
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
            orderService.addOrder(order);
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
