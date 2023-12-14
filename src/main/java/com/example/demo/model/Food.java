package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.Store;
import org.springframework.data.annotation.Id;


@Entity
@Data
@Table(name = "Food")
public class Food {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Column(name = "food_Name")
    private String foodName;

    @Column(name = "category_Id")
    private Long categoryId;

    @Column(name = "category_Name")
    private String categoryName;

    @Column(name = "store_Id")
    private String storeId;

    @ManyToOne
    @JoinColumn (name = "restaurnat_Id",insertable=false, updatable=false)
    private Restaurant restaurant;
}
