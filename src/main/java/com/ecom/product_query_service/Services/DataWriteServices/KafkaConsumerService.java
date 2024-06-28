package com.ecom.product_query_service.Services.DataWriteServices;

import com.ecom.product_query_service.DTO.OperationObject;
import com.ecom.product_query_service.DTO.OperationObjectList;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final OperationHandler operationHandler;

    @KafkaListener(topics = "product-operations", groupId = "product_query_service_group")
    public void listen(OperationObjectList operationObjectList) {

        System.out.println("----------------------------------------------------------------------");
        operationObjectList.getOperationObjects().forEach(operationObject ->
                System.out.println("Received Operation: " + operationObject.getOperationType()));
        System.out.println("----------------------------------------------------------------------");
        operationHandler.handleOperations(operationObjectList);
    }

}
