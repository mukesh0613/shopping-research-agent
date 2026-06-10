package com.mukesh.shoppingresearchagent.provider;

import com.mukesh.shoppingresearchagent.dto.ProductSearchRequest;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;

public interface EcommerceProvider {

    ProductSearchResult search(
            ProductSearchRequest request
    );

    String getProviderName();
}