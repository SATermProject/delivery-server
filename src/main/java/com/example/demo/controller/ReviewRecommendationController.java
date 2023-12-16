package com.example.demo.controller;

import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewRecommendationController {

        private final RecommendationService recommendationService;

        @Autowired
        public ReviewRecommendationController(RecommendationService recommendationService) {
            this.recommendationService = recommendationService;
        }

    @GetMapping("/reviewRecommendation")
    @ResponseBody
    public List<Object[]> recommend() {
        return recommendationService.getRecommendation("reviewRecommendationStrategy");
    }
}