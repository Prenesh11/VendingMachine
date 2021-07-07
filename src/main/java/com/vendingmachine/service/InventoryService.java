package com.vendingmachine.service;

import com.vendingmachine.entity.DenominationEnum;
import com.vendingmachine.entity.Product;
import com.vendingmachine.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public String addProduct(Product product) {
        try {
            if (!inventoryRepository.existsByProductName(product.getProductName()))
            {
                Integer maxId = inventoryRepository.findMaxId();
                product.setId(maxId == null ? 0 : maxId + 1);
                if(isValidDenomination(product.getCost()))
                {
                    inventoryRepository.save(product);
                    return "product record created successfully.";

                }else
                {
                    return"Incorrect product denominations.";
                }
            } else {
                return "product already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean isValidDenomination(int productCost) {
        for (DenominationEnum denomination :DenominationEnum.VALID_DENOMINATIONS)
        {
            if(denomination.getRandValue()==productCost)
            {
                return true;
            }
        }
        return false;
    }

    public List<Product> getAllProducts(){
        return inventoryRepository.findAll();
    }

    public Product findProductByProductCd(String productId){
        return inventoryRepository.findByProductId(productId);
    }

    @Transactional
    public String updateInventory(Product product){
        if (inventoryRepository.existsById(product.getId())){
            try {
                    Product productToBeUpdate = inventoryRepository.findById(product.getId()).get();
                    productToBeUpdate.setProductName(product.getProductName());
                    productToBeUpdate.setQuantity(product.getQuantity());
                    productToBeUpdate.setCost(product.getCost());
                    productToBeUpdate.setProductId(product.getProductId());
                    inventoryRepository.save(productToBeUpdate);
                return "Student record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exists in the database.";
        }
    }


    /**
     * Transactional services
     */

}
