package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<OrderHistory> orderHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<SearchHistory> searchHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Restaurant> restaurants;
}
