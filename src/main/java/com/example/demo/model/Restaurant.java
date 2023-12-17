package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
@Table(name = "Restaurant")
public class Restaurant {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(name = "restaurant_Name")
    private String restaurantName;

    @Column(name = "category_Id")
    private Long categoryId;

    @Column(name = "category_Name")
    private String categoryName;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "restaurant",  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderHistory> orderHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<SearchHistory> searchHistories;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Restaurant() {
    }

    public Restaurant(Long restaurantId, String restaurantName, Long categoryId, String categoryName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
