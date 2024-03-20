package com.example.restaurant.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name= "menu_order")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderMenuRelation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_relation_menu_order")
    private Long id_relation;

    @ManyToOne//(fetch = FetchType.LAZY)
//    @JoinColumn(name="id_menu")
    private Menu menu;

    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name="id_order")
    private Order order;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderMenuRelation() {
    }

    public OrderMenuRelation( Menu menu, Order order, Integer quantity) {
//        this.id_relation = id_relation;
        this.menu = menu;
        this.order = order;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId_relation() {
        return id_relation;
    }

    public void setId_relation(Long id_relation) {
        this.id_relation = id_relation;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderMenuRelation{" +
                "id_relation=" + id_relation +
                ", menu=" + menu +
                ", quantity=" + quantity +
                '}';
    }
}
