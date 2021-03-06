package com.vendingmachine.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * PurchaseResponse object is responsible for holding purchase response structure
 *
 * @author  Prenesh Naidoo
 * @version 1.0
 * @since  2021
 */

public class PurchaseResponse {


    private String purchaseStatus;
        private List<CashFlow> purchaseChangeBreakdown = new ArrayList<>();
    private int Change;

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public int getChange() {
        return Change;
    }

    public void setChange(int change) {
        Change = change;
    }

    public List<CashFlow> getPurchaseChangeBreakdown() {
        return purchaseChangeBreakdown;
    }

    public void setPurchaseChangeBreakdown(List<CashFlow> purchaseChangeBreakdown) {
        this.purchaseChangeBreakdown = purchaseChangeBreakdown;
    }

}
