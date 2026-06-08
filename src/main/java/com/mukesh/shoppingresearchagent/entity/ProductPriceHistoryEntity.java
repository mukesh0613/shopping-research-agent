package com.mukesh.shoppingresearchagent.entity;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;


@Entity
@Table(name="product_price_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPriceHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String productName;
    private String source;
    private Double price;
    private LocalDateTime capturedAt;
}
