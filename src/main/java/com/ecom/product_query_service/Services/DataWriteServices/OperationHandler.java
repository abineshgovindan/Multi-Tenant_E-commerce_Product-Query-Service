package com.ecom.product_query_service.Services.DataWriteServices;

import com.ecom.product_query_service.DTO.OperationObject;
import com.ecom.product_query_service.DTO.OperationObjectList;
import com.ecom.product_query_service.Services.DBWriteServices.CategoryService;
import com.ecom.product_query_service.Services.DBWriteServices.ProductWriteService;
import com.ecom.product_query_service.Services.DBWriteServices.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OperationHandler {
    @Autowired
    private final ProductWriteService productWriteService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProductVariantService productVariantService;
    @Autowired
    private final ThreadPoolTaskExecutor taskExecutor;
    OperationHandler(ProductWriteService productWriteService, CategoryService categoryService, ProductVariantService productVariantService, ThreadPoolTaskExecutor threadPoolExecutor){
        this.productWriteService = productWriteService;
        this.categoryService = categoryService;
        this.productVariantService = productVariantService;
        this.taskExecutor = threadPoolExecutor;
        this.taskExecutor.setCorePoolSize(10);
        this.taskExecutor.setMaxPoolSize(20);
        this.taskExecutor.initialize();

    }

    public void handleOperations(OperationObjectList operationObjectList) {
    List<CompletableFuture<Void>> futures = operationObjectList.getOperationObjects().stream()
            .collect(Collectors.groupingBy(OperationObject::getOperationType))
            .entrySet()
            .stream()
            .map(entry ->{
                switch (entry.getKey()){
                    case "ADD_PRODUCTS":
                    case "UPDATE_PRODUCTS":
                    case "DELETE_PRODUCTS":
                        return CompletableFuture.runAsync(() -> productWriteService.processOperations(entry.getValue()), taskExecutor);
                    case "ADD_CATEGORY_PRODUCTS":
                    case "UPDATE_CATEGORY_PRODUCTS":
                    case "DELETE_CATEGORY_PRODUCTS":
                    case "ADD_LIST_CATEGORY_PRODUCTS":
                    case "UPDATE_LIST_CATEGORY_PRODUCTS":
                    case "DELETE_LIST_CATEGORY_PRODUCTS":
                        return CompletableFuture.runAsync(()-> categoryService.processOperations(entry.getValue()), taskExecutor);
                    case "ADD_PRODUCTS_VARIENT":
                    case "UPDATE_PRODUCTS_VARIENT":
                    case "DELETE_PRODUCTS_VARIENT":
                    case "ADD_LIST_PRODUCTS_VARIENT":
                    case "UPDATE_LIST_PRODUCTS_VARIENT":
                        return CompletableFuture.runAsync(() -> productVariantService.processOperations(entry.getValue()), taskExecutor);
                    default:
                        throw  new IllegalArgumentException("Unsupported operation type: " + entry.getKey());

                }
            })
            .collect(Collectors.toList());
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

}
