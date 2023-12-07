package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.model.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RecommendService recommendService;

    @PostMapping("/review")
    public Review createReview(@RequestBody Review review)
    {
        return reviewRepository.save(review);
    }

    @GetMapping("/review/{ID}")
    public String readReview(@PathVariable Integer ID)
    {
        Optional<Review> reviewOptional = reviewRepository.findById(ID);
        reviewOptional.ifPresent(System.out::println);

        return "successfully executed 1";
    }

    @PostMapping("/food")
    public Food createFood(@RequestBody Food food)
    {
        return foodRepository.save(food);
    }

    @GetMapping("/food/{ID}")
    public String readFood(@PathVariable Integer ID)
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
    public String readRestaurant(@PathVariable Integer ID)
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(ID);
        restaurantOptional.ifPresent(System.out::println);

        return "successfully executed 3";
    }

    @GetMapping("/recommendations")
    public String showRecommendation()
    {
       List<Food> recommendedRestaurants = recommendService.getRecommendedRestaurants();

       for (Food restaurant : recommendedRestaurants)
       {
           System.out.println("Result: " + restaurant.getName());
       }

       return "redirect:/";

    }
}
