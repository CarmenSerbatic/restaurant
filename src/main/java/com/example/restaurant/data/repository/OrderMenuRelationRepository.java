package com.example.restaurant.data.repository;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.entity.OrderMenuRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRelationRepository extends JpaRepository<OrderMenuRelation, Long> {
}
