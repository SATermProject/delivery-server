package com.example.demo.service;

import com.example.demo.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService{
    @Autowired
    private RatingRecommendationStrategy recommendationStrategy;

    public List<Food> getRecommendedRestaurants() {
        return recommendationStrategy.recommend();
    }
}
