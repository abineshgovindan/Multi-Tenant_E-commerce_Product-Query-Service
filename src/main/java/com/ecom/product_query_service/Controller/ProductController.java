package com.ecom.product_query_service.Controller;

import com.ecom.product_query_service.Collection.Product;
import com.ecom.product_query_service.Services.QueryService.QueryServiceInterface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;



    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Product>> getProductsByStoreId(@PathVariable UUID storeId) {
        List<Product> products = productService.getProductsByStoreId(storeId);
        return ResponseEntity.ok(products);
    }
}
