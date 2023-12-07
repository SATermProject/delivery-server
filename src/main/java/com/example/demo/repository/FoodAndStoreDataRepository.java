package com.example.demo.repository;

import com.example.demo.model.FoodAndStoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodAndStoreDataRepository extends JpaRepository<FoodAndStoreData, Long> {

    @Query("SELECT f FROM FoodAndStoreData f WHERE f.categoryName LIKE %:keyword% OR f.storeName LIKE %:keyword% OR f.foodName LIKE %:keyword%")
    List<FoodAndStoreData> searchByFoodNameOrCategoryNameOrStoreName1Containing(@Param("keyword") String keyword);

    @Query("SELECT f FROM FoodAndStoreData f WHERE f.categoryName LIKE %:mostFrequentKeyword% OR f.storeName LIKE %:mostFrequentKeyword OR f.foodName LIKE %:mostFrequentKeyword")
    List<FoodAndStoreData> searchByFoodNameOrCategoryNameOrStoreNameContaining(String mostFrequentKeyword);
    @Query("SELECT f FROM FoodAndStoreData f WHERE f.categoryId = :categoryId")
    List<FoodAndStoreData> searchByCategoryIdContaining(Long categoryId);
}