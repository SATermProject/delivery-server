package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation/review/{userId}")
    @ResponseBody
    public List<Restaurant> ReviewRecommend(@PathVariable(value = "userId") Long userId) {
        return recommendationService.getRecommendation("reviewRecommendationStrategy", userId);
    }

    @GetMapping("/recommendation/search/{userId}")
    @ResponseBody
    public List<Restaurant> searchRecommend(@PathVariable(value = "userId") Long userId) {
        return recommendationService.getRecommendation("searchRecommendationStrategy", userId);
    }

    @GetMapping("/recommendation/order/{userId}")
    @ResponseBody
    public List<Restaurant> orderRecommend(@PathVariable(value = "userId") Long userId) {
        return recommendationService.getRecommendation("orderRecommendationStrategy", userId);
    }

    @GetMapping("/recommendation/rating/{userId}")
    @ResponseBody
    public List<Restaurant> ratingRecommend(@PathVariable(value = "userId") Long userId) {
        return recommendationService.getRecommendation("ratingRecommendationStrategy", userId);
    }
}
