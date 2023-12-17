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

    public List<String> getAllRestaurant() {
        List<Restaurant> allStores = restaurantRepository.findAll();
        List<String> restaurantDetails = new ArrayList<>();

        for (Restaurant store : allStores) {
            String details = "Restaurant ID: " + store.getRestaurantId()
                    + ", Name: " + store.getRestaurantName()
                    + ", Category ID: " + store.getCategoryId()
                    + ", Category Name: " + store.getCategoryName();
            restaurantDetails.add(details);
        }
        return restaurantDetails;
    }
}
