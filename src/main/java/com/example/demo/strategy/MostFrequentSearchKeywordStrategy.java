package com.example.demo.strategy;

import com.example.demo.model.FoodAndStoreData;
import com.example.demo.repository.FoodAndStoreDataRepository;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.strategy.RecommendStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MostFrequentSearchKeywordStrategy implements RecommendStrategy {
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private FoodAndStoreDataRepository foodAndStoreDataRepository;
    private String keyword;

    public MostFrequentSearchKeywordStrategy(String keyword){
        this.keyword = keyword;
    }

    @Override
    public List<FoodAndStoreData> recommend(String keyword) {
        String mostFrequentKeyword = searchHistoryRepository.findMostFrequentSearchKeyword();
        List<FoodAndStoreData> matchingDataList = foodAndStoreDataRepository.searchByFoodNameOrCategoryNameOrStoreNameContaining(mostFrequentKeyword);

        if (!matchingDataList.isEmpty()) {
            FoodAndStoreData firstMatchingData = matchingDataList.get(0);
            Long categoryId = firstMatchingData.getCategoryId();
            return foodAndStoreDataRepository.searchByCategoryIdContaining(categoryId);
        }
        return Collections.emptyList();
    }

}
