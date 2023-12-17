package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class SearchRecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public SearchRecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/searchRecommendation")
    @ResponseBody
    public List<Restaurant> recommend() {
        return recommendationService.getRecommendation("searchRecommendationStrategy");
    }
}
