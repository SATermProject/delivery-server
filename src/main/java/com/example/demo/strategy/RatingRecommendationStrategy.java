package com.example.demo.strategy;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;

@Component
public class RatingRecommendationStrategy implements RecommendationStrategy {
    @Autowired
    private FoodRepository foodRepository; // Food 엔터티를 다루기 위한 Repository
    @Autowired
    private ReviewRepository reviewRepository; // Review 엔터티를 다루기 위한 Repository
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public List<Restaurant> recommend(Long userID) {
        // 모든 가게의 리뷰와 평점 정보 가져오기
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        // 각 가게의 리뷰 평균 평점 계산하여 내림차순으로 정렬
        allRestaurants.sort(Comparator.comparingDouble(this::calculateAverageRating).reversed());

        return allRestaurants;
    }
    private double calculateAverageRating(Restaurant restaurant) {
        // 해당 가게의 모든 리뷰 가져오기
        List<Review> reviews = reviewRepository.findByRestaurant_RestaurantId((restaurant.getRestaurantId()));

        // 리뷰가 없으면 기본값 반환
        if (reviews.isEmpty()) {
            return 0.0;
        }

        // 모든 리뷰의 평점 합산 및 평균 계산
        double sum = reviews.stream().mapToDouble(Review::getRating).sum();
        return sum / reviews.size();
    }
}
