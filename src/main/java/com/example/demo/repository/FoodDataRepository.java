package com.example.demo.repository;

import com.example.demo.model.FoodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodDataRepository extends JpaRepository<FoodData, Long> {

    @Query("SELECT DISTINCT f.store.storeName FROM FoodData f WHERE f.foodName LIKE %:keyword% OR f.categoryName LIKE %:keyword% OR f.foodName LIKE CONCAT('%', :keyword, '%') OR f.categoryName LIKE CONCAT('%', :keyword, '%')")
    List<String> findByStoreNamesFromFoodContaining(@Param("keyword") String keyword);}
