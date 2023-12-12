package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "store_Data")
public class StoreData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_List;

    @Column(name = "store_Name")
    private String storeName;

    @Column(name = "store_Id")
    private Long storeId;

    @Column(name = "category_Id")
    private Long categoryId;

    @Column(name = "category_Name")
    private String categoryName;

    @OneToMany(mappedBy = "store")
    private List<FoodData> foods;
}

