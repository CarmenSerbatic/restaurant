package com.example.restaurant.service;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.repository.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDAO menuDAO;
    @Override
    public List<Menu> findAllMenus() {
        return menuDAO.findAll();
    }

    @Override
    public Menu findMenuById(int id) {
        Menu menu = null;
        Optional<Menu> result = menuDAO.findById(id);

        if (result.isPresent()){
            menu = result.get();
        }

        return menu;
    }

    @Override
    public List<Menu> findMenusDay() {

        return menuDAO.findByDate(LocalDate.now());
    }

    @Override
    public void addMenu(Menu menu) {
        menuDAO.save(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void deleteMenuById(int id) {
        menuDAO.deleteById(id);
    }
}
