package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.model.SearchHistory;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public SearchService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<String> findStoreNamesByKeyword(String keyword) {
        String formattedKeyword = "%" + keyword + "%";
        List<String> foodStoreNames = foodRepository.findByRestaurantNamesFromFoodContaining(formattedKeyword);
        List<String> storeStoreNames = restaurantRepository.findByRestaurantNamesFromRestaurantContaining(formattedKeyword);

        List<String> mergedStoreNames = new ArrayList<>(foodStoreNames);
        mergedStoreNames.addAll(storeStoreNames);

        Set<String> uniqueStoreNames = new HashSet<>();
        uniqueStoreNames.addAll(foodStoreNames);
        uniqueStoreNames.addAll(storeStoreNames);

        return new ArrayList<>(uniqueStoreNames);
    }


    public void addSearchedStoreToSearchHistory(String searchedStoreName) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setSearchedRestaurant(searchedStoreName);
        searchHistoryRepository.save(searchHistory);
    }

    public String retrieveStoreNameById(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        return restaurantOptional.map(Restaurant::getRestaurantName).orElse(null);
    }
}