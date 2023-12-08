package com.example.demo.service;

import com.example.demo.model.FoodAndStoreData;
import com.example.demo.strategy.RecommendStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendContext {

    private final RecommendStrategy recommendStrategy;

    public RecommendContext(@Qualifier(value = "mostFrequentSearchKeywordStrategy") RecommendStrategy recommendStrategy) {
        this.recommendStrategy = recommendStrategy;
    }

    public List<FoodAndStoreData> getRecommendation(String keyword) {
        return recommendStrategy.recommend(keyword);
    }
}

