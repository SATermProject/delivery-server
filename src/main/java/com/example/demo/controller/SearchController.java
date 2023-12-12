package com.example.demo.controller;

import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/search")
    public ResponseEntity<List<String>> findStoreNamesByKeyword(@RequestParam String keyword) {
        List<String> mergedStoreNames = searchService.findStoreNamesByKeyword(keyword);

        if (!mergedStoreNames.isEmpty()) {
            return ResponseEntity.ok(mergedStoreNames);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //여기부터 다시 보면 됨 이제
    @GetMapping("/search/{storeId}")
    public void saveSearchedStoreToSearchHistory(@PathVariable Long storeId) {

        String searchedStoreName = searchService.retrieveStoreNameById(storeId);

        if (searchedStoreName != null) {
            searchService.addSearchedStoreToSearchHistory(searchedStoreName);
        } else {
            // 가게를 찾을 수 없는 경우에 대한 예외 처리
        }
    }
}
