package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Review {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int rating;
    private int restaurantID;
    private int foodID;

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }
}
