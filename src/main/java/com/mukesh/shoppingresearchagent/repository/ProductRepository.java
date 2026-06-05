package com.mukesh.shoppingresearchagent.repository;

import com.mukesh.shoppingresearchagent.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    List<ProductEntity> findByName(String name);
    List<ProductEntity> findByNameContaining(String keyword);
    List<ProductEntity> findByPriceGreaterThan(
            Double price
    );
    List<ProductEntity> findByPriceBetween(
            Double min,
            Double max
    );
}

