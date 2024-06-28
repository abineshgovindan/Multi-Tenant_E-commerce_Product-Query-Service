package com.ecom.product_query_service.Services.QueryService;

import com.ecom.product_query_service.Collection.Product;
import com.ecom.product_query_service.Repository.ProductRepository;
import com.ecom.product_query_service.Services.QueryService.QueryServiceInterface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp  implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getProductsByStoreId(UUID storeId) {
        return productRepository.findByStoreId(storeId);
    }
}
