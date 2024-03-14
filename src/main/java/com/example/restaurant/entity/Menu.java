package com.example.restaurant.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name= "menu")
public class Menu {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_menu")
    private int id_menu;

    @Column(name="date")
    private LocalDate date;
    @Column(name="dish")
    private String dish;

    @Column(name="price")
    private Float price;

    public Menu() {
    }

    public Menu(int id_menu, LocalDate date, String dish, Float price) {
        this.id_menu = id_menu;
        this.date = date;
        this.dish = dish;
        this.price = price;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id_menu=" + id_menu +
                ", date=" + date +
                ", dish='" + dish + '\'' +
                ", price=" + price +
                '}';
    }
}
