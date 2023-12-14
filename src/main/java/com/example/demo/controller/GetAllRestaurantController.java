package com.example.demo.controller;

import com.example.demo.service.GetAllRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAllRestaurantController {

    private GetAllRestaurantService getAllRestaurantService;

    @Autowired
    public GetAllRestaurantController(GetAllRestaurantService getAllRestaurantService) {
        this.getAllRestaurantService = getAllRestaurantService;
    }

    @GetMapping("/getAllRestaurant")
    public List<String> getAllRestaurantName() {
        return getAllRestaurantService.getAllRestaurantName();
    }
}
