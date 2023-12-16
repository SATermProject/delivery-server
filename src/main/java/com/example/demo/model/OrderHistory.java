package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OrderHistory")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id",insertable=false, updatable=false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "restaurant_Id",insertable=false, updatable=false)
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "review_id",insertable=false, updatable=false)
    private Review review;
}
