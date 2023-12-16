package com.example.demo.strategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("searchRecommendationStrategy")
public class SearchRecommendationStrategy implements RecommendStrategy {

    private final EntityManager entityManager;

    @Autowired
    public SearchRecommendationStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Object[]> recommend() {
        String queryString = "SELECT searchedRestaurant " +
                "FROM SearchHistory " +
                "GROUP BY searchedRestaurant " +
                "ORDER BY COUNT(searchedRestaurant) DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class);
        return query.getResultList();
    }
}