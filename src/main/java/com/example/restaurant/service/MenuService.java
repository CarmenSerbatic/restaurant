package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findAllMenus();
    public List<Menu> findMenusDay();

    public Menu findMenuById(int id);

    public void addMenu(Menu menu);
    public void updateMenu(Menu menu);
    public void deleteMenuById(int id);
}
