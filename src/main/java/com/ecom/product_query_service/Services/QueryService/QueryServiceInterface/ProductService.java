package com.ecom.product_query_service.Services.QueryService.QueryServiceInterface;

import com.ecom.product_query_service.Collection.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<Product> getProductsByStoreId(UUID storeId);
}
