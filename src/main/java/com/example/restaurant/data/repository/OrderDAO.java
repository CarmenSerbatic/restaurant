package com.example.restaurant.data.repository;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

}
