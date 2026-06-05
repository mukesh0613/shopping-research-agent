package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.ProductRequestDto;
import com.mukesh.shoppingresearchagent.dto.ProductResponseDto;
import com.mukesh.shoppingresearchagent.entity.ProductEntity;
import com.mukesh.shoppingresearchagent.exception.ProductNotFoundException;
import com.mukesh.shoppingresearchagent.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductResponseDto saveProduct(ProductRequestDto request) {

        log.info("Saving product {}", request.getName());

        ProductEntity product = new ProductEntity();

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        ProductEntity savedProduct =
                productRepository.save(product);

        return new ProductResponseDto(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice()
        );
    }

    public ProductResponseDto getProductById(Long id) {

        log.info("Fetching product {}", id);

        ProductEntity product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product Not Found"
                                ));

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public ProductResponseDto updateProduct(
            Long id,
            ProductRequestDto request
    ) {

        log.info("Updating product {}", id);

        ProductEntity product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product Not Found"
                                ));

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        ProductEntity updatedProduct =
                productRepository.save(product);

        return new ProductResponseDto(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice()
        );
    }

    public void deleteProduct(Long id) {

        log.info("Deleting product {}", id);

        ProductEntity product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product Not Found"
                                ));

        productRepository.delete(product);
    }

    public List<ProductEntity> searchProducts(String keyword) {

        return productRepository.findByNameContaining(keyword);
    }

    public Page<ProductEntity> getProducts(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
}