package com.vendingmachine.repository;

import com.vendingmachine.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Integer> {

    public boolean existsByProductName(String name);

    public boolean existsById(int id);

    public List<Product> findByProductName(String name);

    public Product findByProductId(String product);

    @Query("select max(s.id) from Product s")
    public Integer findMaxId();
}
