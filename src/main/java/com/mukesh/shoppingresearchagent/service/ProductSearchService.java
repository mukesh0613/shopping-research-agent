package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.research.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchService {
    public List<ProductInfo> searchProducts(String query){
        return List.of(

                new ProductInfo(
                        "Lenovo IdeaPad Slim 3",
                        54999.0,
                        "Ryzen 7 processor, 16GB RAM, SSD storage"
                ),

                new ProductInfo(
                        "ASUS Vivobook 15",
                        57999.0,
                        "OLED display, Ryzen 5 processor"
                ),

                new ProductInfo(
                        "HP 15",
                        52999.0,
                        "Intel i5, 16GB RAM, SSD"
                ));
    }
}
