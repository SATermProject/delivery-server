package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "search_History")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long search_Id;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "search_Keyword")
    private String searchKeyword;

    public Long getSearch_Id() {
        return search_Id;
    }

    public void setSearch_Id(Long search_Id) {
        this.search_Id = search_Id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
