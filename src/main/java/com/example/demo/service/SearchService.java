package com.example.demo.service;

import com.example.demo.model.SearchHistory;
import com.example.demo.model.StoreData;
import com.example.demo.repository.FoodDataRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    @Autowired
    private FoodDataRepository foodDataRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public SearchService(FoodDataRepository foodDataRepository) {
        this.foodDataRepository = foodDataRepository;
        this.storeRepository = storeRepository;
    }

    public List<String> findStoreNamesByKeyword(String keyword) {
        String formattedKeyword = "%" + keyword + "%";
        List<String> foodStoreNames = foodDataRepository.findByStoreNamesFromFoodContaining(formattedKeyword);
        List<String> storeStoreNames = storeRepository.findByStoreNamesFromStoreContaining(formattedKeyword);

        List<String> mergedStoreNames = new ArrayList<>(foodStoreNames);
        mergedStoreNames.addAll(storeStoreNames);

        Set<String> uniqueStoreNames = new HashSet<>();
        uniqueStoreNames.addAll(foodStoreNames);
        uniqueStoreNames.addAll(storeStoreNames);

        return new ArrayList<>(uniqueStoreNames);
    }


    public void addSearchedStoreToSearchHistory(String searchedStoreName) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setSearchedStore(searchedStoreName);
        searchHistoryRepository.save(searchHistory);
    }

    public String retrieveStoreNameById(Long storeId) {
        Optional<StoreData> storeOptional = storeRepository.findById(storeId);
        return storeOptional.map(StoreData::getStoreName).orElse(null);
    }
}