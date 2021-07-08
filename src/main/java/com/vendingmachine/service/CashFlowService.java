package com.vendingmachine.service;

import com.vendingmachine.entity.CashFlow;
import com.vendingmachine.entity.DenominationEnum;
import com.vendingmachine.repository.CashFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * this class is responsible for performing most of the DB related queries for cash flow
 *
 * @author  Prenesh Naidoo
 * @version 1.0
 * @since  2021
 */


@Service
public class CashFlowService {

    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Transactional
    public String addNewPettyCashDenomination(CashFlow cashFlow) {
        try {
            if (!cashFlowRepository.existsByDescription(cashFlow.getDescription()))
            {
                Integer maxId = cashFlowRepository.findMaxId();
                cashFlow.setId(maxId == null ? 0 : maxId + 1);
                if(isValidDenomination(cashFlow.getDenomination()))
                {
                    cashFlowRepository.save(cashFlow);
                    return "Cash flow record created successfully.";

                }else
                {
                    return"Incorrect Cash flow denominations.";
                }
            } else {
                return "Cash flow denomination already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String updateCashFlow(CashFlow cashFlow){
        if (cashFlowRepository.existsById(cashFlow.getId())){
            try {
                CashFlow cashFlowToBeUpdated = cashFlowRepository.findById(cashFlow.getId()).get();
                cashFlowToBeUpdated.setDenomination(cashFlow.getDenomination());
                cashFlowToBeUpdated.setDescription(cashFlow.getDescription());
                cashFlowToBeUpdated.setAmount(cashFlow.getAmount());
                cashFlowRepository.save(cashFlowToBeUpdated);
                return "Cash flow updated successfully.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Denomination does not exist in the database.";
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

    public List<CashFlow> getCashFlowBreakDown(){
        return cashFlowRepository.findAll();
    }

    public CashFlow findByDenomination(int value){
        return cashFlowRepository.findByDenomination(value);
    }

    public void updateAddToCashFlow(List<CashFlow> cashFlowList) {

        for (CashFlow valueUpdated : cashFlowList)
        {
            CashFlow cashFlowToBeUpdated = cashFlowRepository.findByDenomination(valueUpdated.getDenomination());
            cashFlowToBeUpdated.setAmount(cashFlowToBeUpdated.getAmount() + valueUpdated.getAmount());
            cashFlowRepository.save(cashFlowToBeUpdated);
        }

    }

    public void updateSubtractFromCashFlow(List<CashFlow> cashFlowList) {

        for (CashFlow valueUpdated : cashFlowList)
        {
            CashFlow cashFlowToBeUpdated = cashFlowRepository.findByDenomination(valueUpdated.getDenomination());
            cashFlowToBeUpdated.setAmount(cashFlowToBeUpdated.getAmount() - valueUpdated.getAmount());
            cashFlowRepository.save(cashFlowToBeUpdated);
        }
    }
}


