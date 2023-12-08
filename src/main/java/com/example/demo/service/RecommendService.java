package com.example.demo.service;

import com.example.demo.model.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.strategy.OrderRecommendationStrategy;
import com.example.demo.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;

    private RecommendationStrategy recommendationStrategy;


    public void setRecommendationStrategy(RecommendationStrategy recommendationStrategy) {
        this.recommendationStrategy = recommendationStrategy;
    }

    public List<Food> recommendFoodsByOrder(Long userId) {
        recommendationStrategy = new OrderRecommendationStrategy(userId);
        return recommendationStrategy.recommend();
    }

    public List<Food> getRecommendedRestaurants() {
        return recommendationStrategy.recommend();
    }

}
