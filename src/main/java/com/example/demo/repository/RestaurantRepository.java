package com.example.demo.repository;

import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE :keyword OR r.categoryName LIKE :keyword")
    List<Restaurant> findRestaurantsByRestaurantNamesFromRestaurantContaining(@Param("keyword") String keyword);
}
