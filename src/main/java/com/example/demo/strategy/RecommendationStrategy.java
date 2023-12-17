package com.example.demo.strategy;

import com.example.demo.model.Restaurant;

import java.util.List;

public interface RecommendationStrategy {
    List<Restaurant> recommend();
}
