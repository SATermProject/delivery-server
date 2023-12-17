package com.example.demo.controller;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewController {
    // 리뷰 관련 기능 Controller
    // 1. 사용자의 주문 내역 Get 해오기
    // 2. 해당 주문 목록 중에서 리뷰 작성 가능한 주문 기록 -> 리뷰 작성 (POST)

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

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

            if (order.getReview() != null)
            {
                return new ResponseEntity<>("리뷰가 작성된 주문 내역입니다.", HttpStatus.ACCEPTED);
            }

            // 여기서 리뷰 객체 새로 생성하고 필요한 정보를 설정
            Review review = new Review();
            review.setRating(reviewRequest.getRating());
            review.setContent(reviewRequest.getContent());
            review.setUser(order.getUser());
            review.setRestaurant(restaurant);

            // 기존 order 객체에 review 아이디 설정
            order.setReview(review);

            // 리뷰를 저장
            reviewRepository.save(review);

            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
