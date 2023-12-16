package com.example.demo.service;

import com.example.demo.strategy.RecommendStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    @Autowired
    private Map<String, RecommendStrategy> strategies;

    @Autowired
    public RecommendationService(Map<String, RecommendStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<Object[]> getRecommendation(String strategyName) {
        RecommendStrategy strategy = strategies.get(strategyName);
        if (strategy != null) {
            return strategy.recommend();
        } else {
            throw new IllegalArgumentException("Invalid strategy name");
        }
    }
}
