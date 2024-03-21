package com.example.restaurant.data.repository;


import com.example.restaurant.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByDate(LocalDate date);
    List<Order> findByFinalizedAndDate(Boolean finalized, LocalDate date);

}
