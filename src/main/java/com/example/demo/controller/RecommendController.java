package com.example.demo.controller;

import com.example.demo.model.FoodAndStoreData;
import com.example.demo.repository.FoodAndStoreDataRepository;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private FoodAndStoreDataRepository foodAndStoreDataRepository;

    @GetMapping("/recommendResult")
    public String searchRecommendation(Model model) {

        String mostFrequentKeyword = searchHistoryRepository.findMostFrequentSearchKeyword();

        List<FoodAndStoreData> matchingDataList = foodAndStoreDataRepository.searchByFoodNameOrCategoryNameOrStoreNameContaining(mostFrequentKeyword);

        if (!matchingDataList.isEmpty()) {

            FoodAndStoreData firstMatchingData = matchingDataList.get(0);
            String mostFrequentFoodName = firstMatchingData.getFoodName();
            String mostFrequentStoreName = firstMatchingData.getStoreName();

            model.addAttribute("mostFrequentFoodName", mostFrequentFoodName);
            model.addAttribute("mostFrequentStoreName", mostFrequentStoreName);

            Long categoryId = matchingDataList.get(0).getCategoryId();

            List<FoodAndStoreData> otherDataList = foodAndStoreDataRepository.searchByCategoryIdContaining(categoryId);

            model.addAttribute("otherDataList", otherDataList);
        }

        return "recommendResult"; // 결과를 보여줄 뷰 페이지 이름
    }
}
