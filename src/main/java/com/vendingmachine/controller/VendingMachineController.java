package com.vendingmachine.controller;

import com.vendingmachine.entity.*;
import com.vendingmachine.service.CashFlowService;
import com.vendingmachine.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class VendingMachineController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CashFlowService cashFlowService;



    @RequestMapping(value = "createProduct", method = RequestMethod.POST)
    public String createProduct(@RequestBody Product product){
        return inventoryService.addProduct(product);
    }


    @RequestMapping(value = "retrieveAllProducts", method = RequestMethod.GET)
    public List<Product> viewAllProducts(){
        return inventoryService.getAllProducts();
    }

    @RequestMapping(value = "updateProduct", method = RequestMethod.PUT)
    public String updateProduct(@RequestBody Product product){
        return inventoryService.updateInventory(product);
    }




    @RequestMapping(value = "addCashFlow", method = RequestMethod.POST)
    public String createCashFlow(@RequestBody CashFlow cashFlow){
        return cashFlowService.addNewPettyCashDenomination(cashFlow);
    }

    @RequestMapping(value = "retrieveCashFlowBreakDown", method = RequestMethod.GET)
    public List<CashFlow> retrieveCashFlow(){
        return cashFlowService.getCashFlowBreakDown();
    }

    @RequestMapping(value = "updateCashFlow", method = RequestMethod.PUT)
    public String updateCashFlow(@RequestBody CashFlow cashFlow){
        return cashFlowService.updateCashFlow(cashFlow);
    }





    @RequestMapping(value = "purchaseItem", method = RequestMethod.POST)
    public PurchaseResponse purchaseItem(@RequestBody PurchaseRequest purchaseRequest){

        Product desiredProduct = inventoryService.findProductByProductCd(purchaseRequest.getProductCd());
        PurchaseResponse purchaseResponse = new PurchaseResponse();

        if(purchaseRequest.getTotal() >= desiredProduct.getCost() && desiredProduct.getQuantity()>=1)
        {
            desiredProduct.setQuantity(desiredProduct.getQuantity()-1);
            updateProduct(desiredProduct);

            purchaseResponse.setPurchaseStatus("Successful");
            purchaseResponse.setChange(purchaseRequest.getTotal() - desiredProduct.getCost());
//            purchaseResponse.setPurchaseChangeBreakdown(calculateBreakDown(purchaseResponse.getChange()));



        }
        else
        {
            purchaseResponse.setChange(purchaseRequest.getTotal());
            purchaseResponse.setPurchaseStatus("Failed Purchase");
            purchaseResponse.setPurchaseChangeBreakdown(purchaseRequest.getAmountList());
        }
        return purchaseResponse;
    }

    @RequestMapping(value = "calculateChange", method = RequestMethod.GET)
    private List<CashFlow> calculateBreakDown() {

        int change =55;

        List<CashFlow> currentCashFlow = retrieveCashFlow();
        List<CashFlow> purchaseChangeBreakdown = new ArrayList<>();

        CashFlow fiveRand = new CashFlow();
        fiveRand.setAmount(0);
        fiveRand.setDescription("Five rand coin");
        fiveRand.setDenomination(DenominationEnum.FIVE_RAND.getRandValue());
        purchaseChangeBreakdown.add(fiveRand);

        CashFlow tenRand = new CashFlow();
        tenRand.setAmount(0);
        tenRand.setDescription("Ten rand note");
        tenRand.setDenomination(DenominationEnum.TEN_RAND.getRandValue());
        purchaseChangeBreakdown.add(tenRand);

        CashFlow twentyRand = new CashFlow();
        twentyRand.setAmount(0);
        twentyRand.setDescription("Twenty rand note");
        twentyRand.setDenomination(DenominationEnum.TWENTY_RAND.getRandValue());
        purchaseChangeBreakdown.add(twentyRand);


        while(change >= DenominationEnum.TWENTY_RAND.getRandValue())
        {
            if(change/DenominationEnum.TWENTY_RAND.getRandValue() >= 1)
            {
                purchaseChangeBreakdown.get(0).setAmount( purchaseChangeBreakdown.get(0).getAmount() + 1);
                change -= DenominationEnum.TWENTY_RAND.getRandValue();
            }
        }

        while(change>= DenominationEnum.TEN_RAND.getRandValue())
        {
            if(change/DenominationEnum.TEN_RAND.getRandValue() >= 1)
            {
                purchaseChangeBreakdown.get(1).setAmount( purchaseChangeBreakdown.get(1).getAmount() + 1);
                change -= DenominationEnum.TEN_RAND.getRandValue();
            }
        }

        while(change>= DenominationEnum.FIVE_RAND.getRandValue())
        {
            if(change/DenominationEnum.FIVE_RAND.getRandValue() >= 1)
            {
                purchaseChangeBreakdown.get(2).setAmount( purchaseChangeBreakdown.get(2).getAmount() + 1);
                change -= DenominationEnum.FIVE_RAND.getRandValue();
            }
        }

        return purchaseChangeBreakdown;
    }


    @RequestMapping(value = "populateProductTable", method = RequestMethod.GET)
    public String populateProductTables()
    {
        List<Product> products = viewAllProducts();
        if (products.size() > 0)
        {
            return "Database is already populated";
        }
        else
        {
            Product coke = new Product();
            coke.setProductName("Coke");
            coke.setCost(20);
            coke.setQuantity(25);
            coke.setProductId("A1");
            createProduct(coke);

            Product chocolate = new Product();
            chocolate.setProductName("chocolate");
            chocolate.setCost(5);
            chocolate.setQuantity(15);
            chocolate.setProductId("A2");
            createProduct(chocolate);

            Product pepsi = new Product();
            pepsi.setProductName("pepsi");
            pepsi.setCost(10);
            pepsi.setQuantity(12);
            pepsi.setProductId("B1");
            createProduct(pepsi);

            Product simbaChips = new Product();
            simbaChips.setProductName("Simba Chips");
            simbaChips.setCost(5);
            simbaChips.setQuantity(40);
            simbaChips.setProductId("B2");
            createProduct(simbaChips);

            return "DataBase has been populated";
        }

    }

    @RequestMapping(value = "populateCashFlowTable", method = RequestMethod.GET)
    public String populateCashFlowTables() {
        List<CashFlow> cashFlows = retrieveCashFlow();
        if (cashFlows.size() > 0) {
            return "Database is already populated";
        } else {
            CashFlow fiveRand = new CashFlow();
            fiveRand.setAmount(30);
            fiveRand.setDescription("Five rand coin");
            fiveRand.setDenomination(DenominationEnum.FIVE_RAND.getRandValue());
            createCashFlow(fiveRand);

            CashFlow tenRand = new CashFlow();
            tenRand.setAmount(15);
            tenRand.setDescription("Ten rand note");
            tenRand.setDenomination(DenominationEnum.TEN_RAND.getRandValue());
            createCashFlow(tenRand);

            CashFlow twentyRand = new CashFlow();
            twentyRand.setAmount(20);
            twentyRand.setDescription("Twenty rand note");
            twentyRand.setDenomination(DenominationEnum.TWENTY_RAND.getRandValue());
            createCashFlow(twentyRand);

            return "DataBase has been populated";
        }
    }


}
