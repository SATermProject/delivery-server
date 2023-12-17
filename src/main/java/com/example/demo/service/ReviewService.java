package com.example.demo.service;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<OrderHistory> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public  int createReview(Long orderId, Review reviewRequest) {
        Optional<OrderHistory> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            OrderHistory order = orderOptional.get();
            Restaurant restaurant = order.getRestaurant();

            if (order.getReview() != null) {
                return 0;
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

            return 1;
        }

        return -1;
    }
}
