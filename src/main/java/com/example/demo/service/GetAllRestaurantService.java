package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllRestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public GetAllRestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<String> getAllRestaurantName() {
        List<Restaurant> allStores = restaurantRepository.findAllBy();
        List<String> storeNames = new ArrayList<>();

        for (Restaurant store : allStores) {
            storeNames.add(store.getRestaurantName());
        }
        return storeNames;
    }
}
