package com.ecom.product_query_service.DTO.OperationDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantImageDTO {
    private UUID variantImageId;
    private String imageUrl;
    private Integer imagePosition;
}
