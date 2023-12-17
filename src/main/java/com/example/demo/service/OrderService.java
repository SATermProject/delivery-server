package com.example.demo.service;

import com.example.demo.model.Food;
import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderHistoryRepository,
                        RestaurantRepository restaurantRepository,
                        FoodRepository foodRepository, UserRepository userRepository) {
        this.orderRepository = orderHistoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    public void saveOrder(Long id, Long restaurantId, Long foodId) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다."));


        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUser(user);
        orderHistory.setRestaurant(restaurant);
        orderHistory.setFood(food);

        orderRepository.save(orderHistory);
    }
}
