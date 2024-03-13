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
    @Column(name="dish1")
    private String dish1;
    @Column(name="dish2")
    private String dish2;
    @Column(name="dish3")
    private String dish3;
    @Column(name="dish4")
    private String dish4;
    @Column(name="price")
    private float price;

    public Menu() {
    }

    public Menu(int id_menu, LocalDate date, String dish1, String dish2, String dish3, String dish4, float price) {
        this.id_menu = id_menu;
        this.date = date;
        this.dish1 = dish1;
        this.dish2 = dish2;
        this.dish3 = dish3;
        this.dish4 = dish4;
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

    public String getDish1() {
        return dish1;
    }

    public void setDish1(String dish1) {
        this.dish1 = dish1;
    }

    public String getDish2() {
        return dish2;
    }

    public void setDish2(String dish2) {
        this.dish2 = dish2;
    }

    public String getDish3() {
        return dish3;
    }

    public void setDish3(String dish3) {
        this.dish3 = dish3;
    }

    public String getDish4() {
        return dish4;
    }

    public void setDish4(String dish4) {
        this.dish4 = dish4;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id_menu=" + id_menu +
                ", date=" + date +
                ", dish1='" + dish1 + '\'' +
                ", dish2='" + dish2 + '\'' +
                ", dish3='" + dish3 + '\'' +
                ", dish4='" + dish4 + '\'' +
                ", price=" + price +
                '}';
    }
}
