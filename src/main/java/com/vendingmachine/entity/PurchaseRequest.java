package com.vendingmachine.entity;


import java.util.ArrayList;
import java.util.List;

public class PurchaseRequest {


    private String productCd;
    private List<CashFlow> amountList = new ArrayList<>();
        private int total = 0;

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public List<CashFlow> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<CashFlow> amountList) {
        this.amountList = amountList;
    }

    public int getTotal() {

        total = 0;

        for (CashFlow amount: amountList)
        {
            total = total += amount.getDenomination()*amount.getAmount();
        }
        return total;
    }

}
