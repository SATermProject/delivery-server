package com.example.demo.strategy;

import com.example.demo.model.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component("reviewRecommendationStrategy")
public class ReviewRecommendationStrategy implements RecommendationStrategy{

    private final EntityManager entityManager;

    @Autowired
    public ReviewRecommendationStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Restaurant> recommend(Long userID) {

        String[] positiveKeywords = {"좋다", "좋아", "좋았", "좋습", "좋음", "맛있어", "맛있다", "맛있음", "맛있었", "맛있습", "만족해", "만족함", "만족했", "만족합", "추천합", "추천해", "추천함"};
        String[] negativeKeywords = {"별로", "맛없", "불만족", "그닥", "비추", "부족", "추천하지 않", "추천 안"};

        // 긍정적 키워드에 해당하는 리뷰 개수 카운트
        String positiveQueryString = "SELECT r.restaurant.restaurantId, COUNT(r.id) " +
                "FROM Review r " +
                "WHERE (" + String.join(" OR ", Arrays.stream(positiveKeywords).map(keyword -> "r.content LIKE '%" + keyword + "%'").toArray(String[]::new)) + ") " +
                "GROUP BY r.restaurant.restaurantId";

        TypedQuery<Object[]> positiveQuery = entityManager.createQuery(positiveQueryString, Object[].class);
        List<Object[]> positiveResultList = positiveQuery.getResultList();

        // 부정적 키워드에 해당하는 리뷰 개수 카운트
        String negativeQueryString = "SELECT r.restaurant.restaurantId, COUNT(r.id) " +
                "FROM Review r " +
                "WHERE (" + String.join(" OR ", Arrays.stream(negativeKeywords).map(keyword -> "r.content LIKE '%" + keyword + "%'").toArray(String[]::new)) + ") " +
                "GROUP BY r.restaurant.restaurantId";

        TypedQuery<Object[]> negativeQuery = entityManager.createQuery(negativeQueryString, Object[].class);
        List<Object[]> negativeResultList = negativeQuery.getResultList();

        // 카운트 매핑 (restaurantId, positiveCount, negativeCount)
        Map<Long, Integer> positiveCountMap = positiveResultList.stream()
                .collect(Collectors.toMap(arr -> ((Number) arr[0]).longValue(), arr -> ((Number) arr[1]).intValue()));

        Map<Long, Integer> negativeCountMap = negativeResultList.stream()
                .collect(Collectors.toMap(arr -> ((Number) arr[0]).longValue(), arr -> ((Number) arr[1]).intValue()));

        // 긍정, 부정 카운트 계산
        Map<Long, Integer> finalCountMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : positiveCountMap.entrySet()) {
            Long restaurantId = entry.getKey();
            int positiveCount = entry.getValue();
            int negativeCount = negativeCountMap.getOrDefault(restaurantId, 0);
            int finalCount = positiveCount - negativeCount;
            finalCountMap.put(restaurantId, finalCount);
        }

        // finalCountMap을 내림차순으로 정렬하여 restaurantId 리스트 생성
        List<Long> sortedRestaurantIds = finalCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= 0) // 음수가 아닌 경우 필터링
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 정렬된 restaurantId를 기준으로 해당 레스토랑 가져오기
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteriaQuery = cb.createQuery(Restaurant.class);
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class);

        // 선택한 열 가져오기
        criteriaQuery.select(root);
        criteriaQuery.where(root.get("restaurantId").in(sortedRestaurantIds));

        List<Restaurant> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return resultList;
    }
}
