package com.example.demo.strategy;

import com.example.demo.model.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("searchRecommendationStrategy")
public class SearchRecommendationStrategy implements RecommendationStrategy {

    private final EntityManager entityManager;

    @Autowired
    public SearchRecommendationStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Restaurant> recommend(Long userID) {
        String queryString = "SELECT DISTINCT NEW com.example.demo.model.Restaurant(r.restaurantId, r.restaurantName, r.categoryId, r.categoryName) " +
                "FROM SearchHistory sh " +
                "JOIN Restaurant r ON sh.searchedRestaurant = r.restaurantName " +
                "WHERE r.restaurantName = sh.searchedRestaurant";

        TypedQuery<Restaurant> query = entityManager.createQuery(queryString, Restaurant.class);
        List<Restaurant> resultList = query.getResultList();
        return resultList;
    }
}