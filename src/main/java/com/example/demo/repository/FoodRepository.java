package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT DISTINCT r.restaurantName " +
            "FROM Food f " +
            "JOIN f.restaurant r " +
            "WHERE f.foodName LIKE %:keyword% OR f.categoryName LIKE %:keyword%")
    List<String> findByRestaurantNamesFromFoodContaining(@Param("keyword") String keyword);

}
