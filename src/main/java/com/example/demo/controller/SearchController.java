package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> findRestaurantByKeyword(@RequestParam(value = "keyword") String keyword) {

        List<Restaurant> foundRestaurants = searchService.findRestaurantByKeyword(keyword);

        if (!foundRestaurants.isEmpty()) {
            return ResponseEntity.ok(foundRestaurants);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/search/saveSearchHistory")
    public void saveSearchedStoreToSearchHistory(@RequestBody Restaurant restaurant) {

        Long restaurantId = restaurant.getRestaurantId();
        String searchedStoreName = searchService.retrieveStoreNameById(restaurantId);

        if (searchedStoreName != null) {
            searchService.addSearchedStoreToSearchHistory(searchedStoreName);
            //System.out.println("검색어 저장 성공");
        } else {
            //System.out.println("검색어 저장 실패");
        }
    }
}
