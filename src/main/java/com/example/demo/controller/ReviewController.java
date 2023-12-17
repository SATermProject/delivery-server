package com.example.demo.controller;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import com.example.demo.service.ReviewService;
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
    private ReviewService reviewService;

    @GetMapping("/orderHistory/user/{userId}")
    ResponseEntity<List<OrderHistory>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderHistory> userOrders = reviewService.getOrdersByUserId(userId);

        if (userOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userOrders, HttpStatus.OK);
        }
    }

    @PostMapping("/review/{orderId}")
    public ResponseEntity<String> createReviewForOrder(@PathVariable Long orderId, @RequestBody Review reviewRequest) {
        int flag = reviewService.createReview(orderId, reviewRequest);

        if (flag == 0) {
            return new ResponseEntity<>("리뷰가 작성된 주문 내역입니다.", HttpStatus.ACCEPTED);
        } else if (flag == 1) {
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
