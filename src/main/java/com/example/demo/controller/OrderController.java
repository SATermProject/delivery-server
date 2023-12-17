package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody Map<String, Long> orderData) {

        Long id = orderData.get("id");
        Long restaurantId = orderData.get("restaurantId");
        Long foodId = orderData.get("foodId");

        orderService.saveOrder(id, restaurantId, foodId);

        return ResponseEntity.ok("주문완료");
    }
}
