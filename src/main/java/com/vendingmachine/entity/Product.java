package com.vendingmachine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Product object mapped to the DB
 *
 * @author  Prenesh Naidoo
 * @version 1.0
 * @since  2021
 */

@Entity
public class Product {

    @Id
    private int id;

    private String productName;
    private int cost;
    private int quantity;
    private String productId;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName.toUpperCase();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId.toUpperCase();
    }

}
