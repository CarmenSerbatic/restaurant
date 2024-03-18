package com.example.restaurant.service;

import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.data.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findMenuById(int id) {
        Menu menu = null;
        Optional<Menu> result = menuRepository.findById(id);

        if (result.isPresent()){
            menu = result.get();
        }

        return menu;
    }

    @Override
    public List<Menu> findMenusDay() {

        return menuRepository.findByDate(LocalDate.now());
    }

    @Override
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void deleteMenuById(int id) {
        menuRepository.deleteById(id);
    }
}
