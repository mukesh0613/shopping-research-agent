package com.mukesh.shoppingresearchagent.tools;

import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;

public interface SearchTool {

    String getToolName();

    ProductSearchResult search(String productName);
}