package com.example.restaurant.data.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "orderes")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id_order;
    @Column(name="date")
    private LocalDate date;

    @ManyToMany
    @JoinTable(name = "menu_order", joinColumns = @JoinColumn(name = "id_order") , inverseJoinColumns = @JoinColumn(name ="id_menu"))
    private List<Menu> menus;
    @Column(name="finalized")
    private boolean finalized = false;

    public Order() {
    }

    public Order(Long id_order, LocalDate date, List<Menu> menus) {
        this.id_order = id_order;
        this.date = date;
        this.menus = menus;
        this.finalized = false;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", date=" + date +
                ", menus=" + menus +
                ", finalized=" + finalized +
                '}';
    }
}
