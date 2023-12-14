package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{restaurantId}/{foodId}/order")
    public ResponseEntity<String> saveOrder(@PathVariable Long restaurantId,
                                             @PathVariable Long foodId) {
        orderService.saveOrder(restaurantId, foodId);
        return ResponseEntity.ok("주문이 완료되었습니다.");
    }
}
