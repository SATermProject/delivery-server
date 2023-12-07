package com.example.demo.repository;

import com.example.demo.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    @Query(value = "SELECT search_Keyword FROM search_History GROUP BY search_Keyword ORDER BY COUNT(search_Keyword) DESC LIMIT 1", nativeQuery = true)
    String findMostFrequentSearchKeyword();
}
