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


    @GetMapping("/search/{storeId}")
    public void saveSearchedStoreToSearchHistory(@PathVariable Long storeId) {

        String searchedStoreName = searchService.retrieveStoreNameById(storeId);

        if (searchedStoreName != null) {
            searchService.addSearchedStoreToSearchHistory(searchedStoreName);
        } else {
            System.out.println("검색어 저장 실패");
        } //혹시나 해서 예외처리 넣긴 했는데 예외가 발생할 일이 없긴함
    }
}
