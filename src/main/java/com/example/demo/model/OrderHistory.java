package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_Id")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "review_id",insertable=false, updatable=false)
    private Review review;
}
