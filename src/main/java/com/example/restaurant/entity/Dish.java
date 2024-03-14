package com.example.restaurant.entity;

import java.time.LocalDate;

public class Dish {

    private String name;
    private LocalDate date;

    public Dish() {
    }

    public Dish(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
