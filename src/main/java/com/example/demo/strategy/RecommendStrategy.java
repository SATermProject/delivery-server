package com.example.demo.strategy;

import com.example.demo.model.FoodAndStoreData;

import java.util.List;

public interface RecommendStrategy {
    List<FoodAndStoreData> recommend(String keyword);
}
