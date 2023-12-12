package com.example.demo.controller;

import com.example.demo.strategy.SearchRecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class SearchRecommendationController {

    private SearchRecommendationStrategy searchRecommendationStrategy;

    @Autowired
    public SearchRecommendationController(SearchRecommendationStrategy searchRecommendationStrategy) {
        this.searchRecommendationStrategy = searchRecommendationStrategy;
    }

    @GetMapping("/searchRecommendation")
    @ResponseBody
    public List<Object[]> recommend() {
        return searchRecommendationStrategy.searchRecommend();
    }

}
