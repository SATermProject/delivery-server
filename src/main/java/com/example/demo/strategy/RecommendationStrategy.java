package com.example.demo.strategy;

import com.example.demo.model.Food;

import java.util.List;

public interface RecommendationStrategy {
    public List<Food> recommend();
}
