package com.example.restaurant.presentation.controller;


import com.example.restaurant.data.entity.Order;

import com.example.restaurant.presentation.dto.Food;
import com.example.restaurant.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * http://localhost:8081/order/
     * @return List<Order>
     */
    @GetMapping("/")
    public ResponseEntity<List<Order>> findAll(){

        List<Order> orders = orderService.findAllOrder();

        if (!orders.isEmpty()){

            return ResponseEntity.ok(orders);

        } else {

            log.error("Not found objects, the list is empty");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Find order by id")
    public ResponseEntity<Order> findOneById(@PathVariable Long id){

        Order order = orderService.findOrderById(id);

        if(order != null){

            return ResponseEntity.ok(order);
        }

        log.error("Not found object with that id");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/day")
    @Operation(description = "Find orders by date")
    public ResponseEntity<List<Order>> findByDate(){

        List<Order> orders = orderService.findOrderDay();

        if (!orders.isEmpty()){

            return ResponseEntity.ok(orders);

        } else {

            log.error("Not found objects, the list is empty");
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/kitchen/{id}")
    @Operation(description = "")
    public ResponseEntity<List<Food>> showOrderToKitchen(@PathVariable Long id){

        Order order = orderService.findOrderById(id);

        if ( order != null) {

            return ResponseEntity.ok(orderService.showOrderToKitchen(order));

        } else {

            log.error("Not found object with that id");
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/finalized")
    @Operation(description = "Find orders by finalized is true")
    public ResponseEntity<List<Order>> findOrderByFinalized(){

        List<Order> orders = orderService.findOrderFinalized();

        if (!orders.isEmpty()){

            return ResponseEntity.ok(orders);

        } else {
            log.error("Not found objects, the list is empty");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Order> create(@RequestBody Order order){

        if(order != null){
            // If exist id, cannot create new menu
            if(orderService.findOrderById(order.getId_order()) != null){

                log.error("There is already an order with that id");
                return ResponseEntity.badRequest().build();
            }

            //Create new order
            order = orderService.addOrder(order);

            log.warn("trying to save an new order");
            return ResponseEntity.ok(order);

        } else {

            log.error("the order was already finished");
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/finalized/{id}")
    public ResponseEntity<Order> changeOrderToFinished(@PathVariable Long id){

        Order order = orderService.findOrderById(id);

            // If exist id, change finalized to true
            if(order != null){

                if (order.getFinalized()){

                    log.error("the order was already finished");
                    return new ResponseEntity<>(order, HttpStatus.NOT_ACCEPTABLE);

                }else{

                    log.warn("trying to update an order with id "+id+" to finished");
                    return ResponseEntity.ok(orderService.changeOrderToFinished(order));
                }

            } else {

                log.error("Not found object with that id");
                return ResponseEntity.notFound().build();

            }

    }

}
