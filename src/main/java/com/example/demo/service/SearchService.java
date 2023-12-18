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
    public SearchService(FoodRepository foodDataRepository) {
        this.foodRepository = foodDataRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findRestaurantByKeyword(String keyword) {

        String formattedKeyword = "%" + keyword + "%";

        List<com.example.demo.model.Restaurant> foodStores = foodRepository.findRestaurantsByRestaurantNamesFromFoodContaining(formattedKeyword);
        List<com.example.demo.model.Restaurant> storeStores = restaurantRepository.findRestaurantsByRestaurantNamesFromRestaurantContaining(formattedKeyword);

        List<com.example.demo.model.Restaurant> mergedStores = new ArrayList<>(foodStores);
        mergedStores.addAll(storeStores);

        Set<com.example.demo.model.Restaurant> uniqueStores = new HashSet<>(mergedStores);

        return new ArrayList<>(uniqueStores);
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