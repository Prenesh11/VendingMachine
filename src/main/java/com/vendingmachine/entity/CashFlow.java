package com.vendingmachine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * CashFlow object mapped to the DB
 *
 * @author  Prenesh Naidoo
 * @version 1.0
 * @since  2021
 */

@Entity
public class CashFlow {

    @Id
    private int id;
    private int denomination;
    private String description;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
