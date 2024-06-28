package com.ecom.product_query_service.DTO.OperationDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomFieldDTO {
    private String fieldName;
    private String fieldValue;
}
