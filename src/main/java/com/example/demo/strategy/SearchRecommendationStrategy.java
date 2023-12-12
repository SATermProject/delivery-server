package com.example.demo.strategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SearchRecommendationStrategy")
public class SearchRecommendationStrategy implements RecommendStrategyInterface{

    private final EntityManager entityManager;

    @Autowired
    public SearchRecommendationStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Object[]> searchRecommend() {
        String queryString = "SELECT searchedStore " +
                "FROM SearchHistory " +
                "GROUP BY searchedStore " +
                "ORDER BY COUNT(searchedStore) DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class);
        return query.getResultList();
    }
}
