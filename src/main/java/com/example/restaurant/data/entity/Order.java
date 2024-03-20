package com.example.restaurant.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name= "orderes")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id_order;
    @Column(name="date")
    private LocalDate date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_relation_menu_order")
    private List<OrderMenuRelation> orderMenuRelations;
    @Column(name="finalized")
    private boolean finalized = false;

    public Order() {
    }

    public Order(LocalDate date, List<OrderMenuRelation> orderMenuRelations, boolean finalized) {
        this.date = date;
        this.orderMenuRelations = orderMenuRelations;
        this.finalized = finalized;
    }

    public Order(LocalDate date, boolean finalized) {
        this.date = date;
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

    public List<OrderMenuRelation> getOrderMenuRelations() {
        return orderMenuRelations;
    }

    public void setOrderMenuRelations(List<OrderMenuRelation> orderMenuRelations) {
        this.orderMenuRelations = orderMenuRelations;
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
//                ", orderMenuRelations=" + orderMenuRelations +
                ", finalized=" + finalized +
                '}';
    }
}
