package com.ecom.product_query_service.DTO.OperationDtos;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private UUID categories_id;
    private UUID storeId;
    private String categoryName;

}
