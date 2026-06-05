package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.entity.ProductEntity;
import com.mukesh.shoppingresearchagent.model.Product;
import com.mukesh.shoppingresearchagent.dto.ProductRequestDto;
import com.mukesh.shoppingresearchagent.service.ProductService;
import com.mukesh.shoppingresearchagent.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class TestController {

    private final ProductService productService;

    public TestController (ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/products")
    public List<ProductEntity> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct ( @Valid @RequestBody ProductRequestDto request){
        return productService.saveProduct(request);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto request
    ) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }

    @GetMapping("/products/search")
    public List<ProductEntity> searchProducts(
            @RequestParam String keyword
    ){
        return productService
                .searchProducts(keyword);
    }

    @GetMapping("/products/paged")
    public Page<ProductEntity> getProducts(@RequestParam int page, @RequestParam int size){
        return productService.getProducts(page,size);
    }
}
