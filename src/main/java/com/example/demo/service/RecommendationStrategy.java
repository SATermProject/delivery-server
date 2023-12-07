package com.example.demo.service;

import com.example.demo.model.Food;

import java.util.List;

public interface RecommendationStrategy {
    public List<Food> recommend();
}
