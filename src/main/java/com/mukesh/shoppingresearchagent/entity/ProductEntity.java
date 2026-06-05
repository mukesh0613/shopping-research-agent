package com.mukesh.shoppingresearchagent.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    public ProductEntity() {
    }

    public ProductEntity(
            Long id,
            String name,
            Double price
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "product")
    private List<ReviewEntity> reviews;
}
