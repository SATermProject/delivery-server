package com.example.demo.service;

import com.example.demo.model.OrderHistory;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<OrderHistory> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
