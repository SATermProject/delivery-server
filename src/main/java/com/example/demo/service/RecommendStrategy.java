package com.example.demo.service;

import com.example.demo.model.FoodAndStoreData;

import java.util.List;

public interface RecommendStrategy {
    List<FoodAndStoreData> recommend(String keyword);
}
