package com.example.restaurant.controller;

import com.example.restaurant.entity.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MenuControllerTest {


    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }



    @Test
    void findAll() {
        ResponseEntity<Menu[]> response  =
                testRestTemplate.getForEntity("/menu/list", Menu[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Menu> menus = Arrays.asList(response.getBody()); //Para modificar un array normal en un Arraylist
        System.out.println(menus.size());

    }

    @Test
    void findOneById() {
        ResponseEntity<Menu> response  =
                testRestTemplate.getForEntity("/menu/25", Menu.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                  "id_menu": 0,
                  "date": "2020-11-03",
                  "dish1": "garbanzos",
                  "dish2": "pasta",
                  "dish3": "ensalada de atun",
                  "dish4": "poke",
                  "price": 6.9
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Menu> response = testRestTemplate.exchange("/menu/", HttpMethod.POST, request, Menu.class);

        Menu result = response.getBody();

        assertEquals(LocalDate.of(2020,11,03), result.getDate());
        assertEquals("garbanzos", result.getDish1());
        assertEquals("pasta", result.getDish2());
        assertEquals("ensalada de atun", result.getDish3());
        assertEquals("poke", result.getDish4());
        assertEquals(6.900000095367432, result.getPrice());
    }
}