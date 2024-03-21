package com.example.restaurant.presentation.controller;

import com.example.restaurant.presentation.dto.Dish;
import com.example.restaurant.data.entity.Menu;
import com.example.restaurant.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    /**
     * List all menus
     * http://localhost:8081/menu/
     * @return List<Menu>
     */
    @GetMapping("/")
    public ResponseEntity<List<Menu>> findAll(){

        List<Menu> menus = menuService.findAllMenus();

        if (!menus.isEmpty()){

            return ResponseEntity.ok(menus);

        } else {
            log.error("Not found objects, the list is empty");
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Find one menu by Id
     * http://localhost:8081/menu/{id}
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    @Operation(description = "Find menu by id")
    public ResponseEntity<Menu> findOneById(@PathVariable int id){

        Menu menu = menuService.findMenuById(id);

        if(menu != null){

            return ResponseEntity.ok(menu);
        }

        log.error("Not found object with that id");
        return ResponseEntity.notFound().build();
    }


    /**
     * Get the dishes of the day
     * http://localhost:8081/menu/day
     * @return ResponseEntity
     */
    @GetMapping("/day")
    @Operation(description = "Find menu by date")
    public ResponseEntity<List<Dish>> findByDate(){

        List<Menu> menus = menuService.findMenusDay();
        List<Dish> dishes = new ArrayList<Dish>();

        if(!menus.isEmpty()){
            for (Menu m : menus){

                Dish dish = new Dish(m.getDish(), m.getPrice());
                dishes.add(dish);
            }

            return ResponseEntity.ok(dishes);
        } else {

            log.error("Not found objects, the list is empty");
            return ResponseEntity.notFound().build();
        }

    }


    /**
     * Create new Menu
     * @param menu
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity<Menu> create(@RequestBody Menu menu){

        if(menu != null){

            // If exist id, cannot create new menu
            if(menuService.findMenuById(menu.getId_menu()) != null){

                log.warn("trying to create a menu with id");
                return ResponseEntity.badRequest().build();
            }

            //Create new menu
            menuService.addMenu(menu);

            return ResponseEntity.ok(menu);
        } else {

            log.error("the object is empty");
            return ResponseEntity.badRequest().build();
        }

    }

}
