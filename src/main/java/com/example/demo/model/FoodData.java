package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "food_Data")
public class FoodData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long food_List;

    @Column(name = "food_Name")
    private String foodName;

    @Column(name = "food_Id")
    private Long foodId;

    @Column(name = "category_Id")
    private Long categoryId;

    @Column(name = "category_Name")
    private String categoryName;

    @Column(name = "store_Id")
    private String storeId;

    @ManyToOne
    @JoinColumn (name = "store_Id",insertable=false, updatable=false)
    private StoreData store;
}

