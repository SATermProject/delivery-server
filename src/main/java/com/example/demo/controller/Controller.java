package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @PostMapping("/reviews/bulk")
    public Iterable<Review> createReviews(@RequestBody List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @GetMapping("/review/{ID}")
    public String readReview(@PathVariable Long ID)
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

    @PostMapping("/foods/bulk")
    public Iterable<Food> createFoods(@RequestBody List<Food> foods) {
        return foodRepository.saveAll(foods);
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

    @PostMapping("/restaurants/bulk")
    public Iterable<Restaurant> createRestaurants(@RequestBody List<Restaurant> restaurants) {
        return restaurantRepository.saveAll(restaurants);
    }

    @GetMapping("/restaurant/{ID}")
    public String readRestaurant(@PathVariable Long ID) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(ID);
        restaurantOptional.ifPresent(System.out::println);

        return "successfully executed 3";
    }

    @GetMapping("/recommendations")
    public String showRecommendation()
    {
       List<Restaurant> recommendedRestaurants = recommendService.getRecommendedRestaurants();

       for (Restaurant restaurant : recommendedRestaurants)
       {
           System.out.println("Result: " + restaurant.getRestaurant_Name());
       }

       return "redirect:/";
    }

    @GetMapping("/orderHistory/user/{userId}")
    ResponseEntity<List<OrderHistory>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderHistory> userOrders = orderRepository.findByUserId(userId);

        /*
        제대로 GET 되는지 확인용
        for (OrderHistory ord : userOrders)
        {
            System.out.println("Result: " + ord.getFood().getFoodName() + " " + ord.getRestaurant().getRestaurant_Name()
             + " " + ord.getUser().getNickname());
        }
         */

        if (userOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userOrders, HttpStatus.OK);
        }
    }

    @PostMapping("/review/{orderId}")
    public ResponseEntity<String> createReviewForOrder(@PathVariable Long orderId, @RequestBody Review reviewRequest) {
        Optional<OrderHistory> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            OrderHistory order = orderOptional.get();
            Restaurant restaurant = order.getRestaurant();

            // 여기서 리뷰를 생성하고 필요한 정보를 설정
            Review review = new Review();
            review.setRating(reviewRequest.getRating());
            review.setContent(reviewRequest.getContent());
            review.setUser(order.getUser());
            review.setRestaurant(restaurant);

            // 리뷰를 저장
            reviewRepository.save(review);

            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
