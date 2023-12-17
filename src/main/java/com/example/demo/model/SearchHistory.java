package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SearchHistory")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;

    @Column(name = "searched_Restaurant")
    private String searchedRestaurant;

    @ManyToOne
    @JsonIgnore
    @JoinColumn (name = "restaurant_id")
    private Restaurant restaurant;
   }
