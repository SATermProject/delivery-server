package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "foodandstore_Data")
public class FoodAndStoreData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodandstore_List;

    @Column (name = "store_Name")
    private String storeName;

    @Column (name = "food_Name")
    private String foodName;

    @Column (name = "store_Id")
    private Long storeId;

    @Column (name = "food_Id")
    private Long foodId;

    @Column (name = "category_Id")
    private Long categoryId;

    @Column (name = "category_Name")
    private String categoryName;

    //getter, setter

    public Long getFoodandstore_List() {
        return foodandstore_List;
    }

    public void setFoodandstore_List(Long foodandstore_List) {
        this.foodandstore_List = foodandstore_List;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}