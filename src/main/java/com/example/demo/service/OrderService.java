package com.example.demo.service;

import com.example.demo.model.Food;
import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public OrderService(OrderRepository orderHistoryRepository,
                        RestaurantRepository restaurantRepository,
                        FoodRepository foodDataRepository) {
        this.orderRepository = orderHistoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodDataRepository;
    }

    public void saveOrder(Long restaurantId, Long foodId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);

        if (restaurant != null && food != null) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setRestaurant(restaurant);
            orderHistory.setFood(food);

            orderRepository.save(orderHistory);
        } else {
            // 예외처리
        }
    }
}
