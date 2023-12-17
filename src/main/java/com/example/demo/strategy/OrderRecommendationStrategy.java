package com.example.demo.strategy;

import com.example.demo.model.Food;
import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class OrderRecommendationStrategy implements RecommendationStrategy {

    //자동 DI
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodRepository foodRepository;

    private Long userId;

    public List<Restaurant> recommend(Long userId) {

        // userId가 null인지 체크하고 처리하는 로직 추가
        if (userId == null) {
            // 예외 처리 또는 기본값 설정 등
            throw new IllegalArgumentException("userId cannot be null");
        }

        List<OrderHistory> orders = orderRepository.findByUserId(userId);

        // 레스토랑별로 음식 주문 횟수 및 총 주문 횟수를 저장할 맵
        Map<Restaurant, Map<Food, Integer>> orderCountPerRestaurant = new HashMap<>();
        Map<Restaurant, Integer> totalOrderCountPerRestaurant = new HashMap<>();

        for (OrderHistory order : orders) {
            Food food = order.getFood();
            Restaurant restaurant = food.getRestaurant();

            orderCountPerRestaurant.putIfAbsent(restaurant, new HashMap<>());
            Map<Food, Integer> foodOrderCounts = orderCountPerRestaurant.get(restaurant);
            foodOrderCounts.put(food, foodOrderCounts.getOrDefault(food, 0) + 1);
            totalOrderCountPerRestaurant.put(restaurant, totalOrderCountPerRestaurant.getOrDefault(restaurant, 0) + 1);
        }

        // 총 주문 횟수에 따라 레스토랑 정렬
        List<Restaurant> sortedRestaurants = new ArrayList<>(totalOrderCountPerRestaurant.keySet());
        sortedRestaurants.sort((r1, r2) -> totalOrderCountPerRestaurant.get(r2).compareTo(totalOrderCountPerRestaurant.get(r1)));

        List<Food> recommendedFoods = new ArrayList<>();

        for (Restaurant restaurant : sortedRestaurants) {
            Map<Food, Integer> foodCounts = orderCountPerRestaurant.get(restaurant);

            // 각 레스토랑에서 가장 많이 주문된 음식 찾기
            Food mostOrderedFood = Collections.max(foodCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
            recommendedFoods.add(mostOrderedFood);
        }

        return sortedRestaurants;
    }

}
