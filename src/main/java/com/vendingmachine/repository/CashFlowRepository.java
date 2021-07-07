package com.vendingmachine.repository;

import com.vendingmachine.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Integer> {

    public boolean existsByDescription(String name);

    public List<CashFlow> findByDescription(String name);

    public CashFlow findByDenomination(int value);


    @Query("select max(s.id) from CashFlow s")
    public Integer findMaxId();
}
