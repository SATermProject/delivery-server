package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.model.Food;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    // 데이터베이스 테스트용 임시 Controller

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/user")
    public User createUser(@RequestBody User user) { return userRepository.save(user);}

    @Transactional
    @PostMapping("/review")
    public Review createReview(@RequestBody Review review)
    {
        return reviewRepository.save(review);
    }

    @PostMapping("/food")
    public Food createFood(@RequestBody Food food)
    {
        return foodRepository.save(food);
    }

    @GetMapping("/food/{ID}")
    public String readFood(@PathVariable Long ID)
    {
        Optional<Food> foodOptional = foodRepository.findById(ID);
        foodOptional.ifPresent(System.out::println);

        return "successfully executed 2";
    }

    @PostMapping("/restaurant")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant)
    {
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurant/{ID}")
    public String readRestaurant(@PathVariable Long ID)
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(ID);
        restaurantOptional.ifPresent(System.out::println);

        return "successfully executed 3";
    }

}
