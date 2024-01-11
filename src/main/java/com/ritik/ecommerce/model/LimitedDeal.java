package com.ritik.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
public class LimitedDeal extends Deal {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    String description;
    Long itemId;
    Long itemSoldLimit;
    LocalDateTime expiryTime;
    Double price;

    public LimitedDeal(String description, Long itemId, Long itemSoldLimit, LocalDateTime expiryTime, Double price) {
        this.description = description;
        this.itemId = itemId;
        this.itemSoldLimit = itemSoldLimit;
        this.expiryTime = expiryTime;
        this.price = price;
    }

    public LimitedDeal() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemSoldLimit() {
        return itemSoldLimit;
    }

    public void setItemSoldLimit(Long itemSoldLimit) {
        this.itemSoldLimit = itemSoldLimit;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
