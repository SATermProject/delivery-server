package com.example.demo.repository;

import com.example.demo.model.StoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreData, Long> {

    @Query("SELECT s.storeName FROM StoreData s WHERE s.storeName LIKE %:keyword% OR s.categoryName LIKE %:keyword%" )
    List<String> findByStoreNamesFromStoreContaining(@Param("keyword") String keyword);

}