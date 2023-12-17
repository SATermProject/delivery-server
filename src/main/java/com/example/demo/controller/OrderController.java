package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.saveOrder(orderRequest.getId(), orderRequest.getRestaurantId(), orderRequest.getFoodId());

        return ResponseEntity.ok("주문완료");
    }
}
