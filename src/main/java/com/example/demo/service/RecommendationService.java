package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    @Autowired
    private Map<String, RecommendationStrategy> strategies;

    private Long userID;

    @Autowired
    public RecommendationService(Map<String, RecommendationStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<Restaurant> getRecommendation(String strategyName, Long userID) {
        this.userID = userID;
        RecommendationStrategy strategy = strategies.get(strategyName);
        if (strategy != null) {
            return strategy.recommend(userID);
        } else {
            throw new IllegalArgumentException("Invalid strategy name");
        }
    }
}
