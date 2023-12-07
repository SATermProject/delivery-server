package com.example.demo.controller;

import com.example.demo.model.FoodAndStoreData;
import com.example.demo.model.SearchHistory;
import com.example.demo.repository.FoodAndStoreDataRepository;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Controller
@RequestMapping(value = "/api", produces = "text/html;charset=UTF-8")
public class SearchController {
    private final SearchHistoryRepository searchHistoryRepository;
    private final FoodAndStoreDataRepository foodAndStoreDataRepository;

    @Autowired
    public SearchController(SearchHistoryRepository searchHistoryRepository, FoodAndStoreDataRepository foodAndStoreDataRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
        this.foodAndStoreDataRepository = foodAndStoreDataRepository;
    }

    private static final Long default_user_id = 1L;


    @PostMapping(value = "/search", produces = "text/html;charset=UTF-8")
    public ResponseEntity<String> saveSearchKeyword(@RequestParam String keyword) throws UnsupportedEncodingException {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setUserId(default_user_id);
        searchHistory.setSearchKeyword(keyword);
        searchHistoryRepository.save(searchHistory);

        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());
        String redirectUrl = "/api/search/foodandstore?keyword=" + encodedKeyword;
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", redirectUrl)
                .build();
    }

    @GetMapping(value = "/search/foodandstore" , produces = "text/html;charset=UTF-8")
    public String searchByKeyword (@RequestParam("keyword") String keyword, Model model) {
        
        List<FoodAndStoreData> searchResult = foodAndStoreDataRepository.searchByFoodNameOrCategoryNameOrStoreName1Containing(keyword);

        model.addAttribute("searchResult", searchResult);
        return "searchResult";
    }
}