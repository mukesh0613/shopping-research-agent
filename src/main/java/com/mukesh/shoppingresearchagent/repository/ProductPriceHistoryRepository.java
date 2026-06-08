package com.mukesh.shoppingresearchagent.repository;

import com.mukesh.shoppingresearchagent.entity.ProductPriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistoryEntity, Long> {
    List<ProductPriceHistoryEntity>
    findByProductName(String productName);

    Optional<ProductPriceHistoryEntity>
    findFirstByProductNameOrderByPriceAsc(
            String productName
    );

    Optional<ProductPriceHistoryEntity>
    findFirstByProductNameOrderByCapturedAtDesc(
            String productName
    );

    @Query("""
SELECT AVG(p.price)
FROM ProductPriceHistoryEntity p
WHERE p.productName = :productName
""")
    Double getAveragePrice(
            @Param("productName")
            String productName
    );

    List<ProductPriceHistoryEntity>
    findTop7ByProductNameOrderByCapturedAtDesc(
            String productName
    );
}
