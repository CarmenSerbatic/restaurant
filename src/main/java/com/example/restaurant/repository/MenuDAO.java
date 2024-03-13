package com.example.restaurant.repository;

import com.example.restaurant.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDAO extends JpaRepository<Menu, Integer> {
}
