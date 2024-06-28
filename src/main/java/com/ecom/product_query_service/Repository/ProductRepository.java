package com.ecom.product_query_service.Repository;

import com.ecom.product_query_service.Collection.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    List<Product> findByStoreId(UUID storeId);

}
