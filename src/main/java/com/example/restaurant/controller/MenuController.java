package com.example.restaurant.controller;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    /**
     * List all menus
     * http://localhost:8081/menu/list
     * @return List<Menu>
     */
    @GetMapping("/list")
    public List<Menu> findAll(){
        return menuService.findAllMenus();
    }

    @GetMapping("/{id}")
    @Operation(description = "Find menu by id")
    public ResponseEntity<Menu> findOneById(@PathVariable int id){
        Menu menu = menuService.findMenuById(id);
        if(menu != null){
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.notFound().build();
    }

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
            return ResponseEntity.badRequest().build();
        }

    }

}
