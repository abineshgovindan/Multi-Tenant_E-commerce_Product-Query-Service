package com.ecom.product_query_service.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    @Field(name = "category_Id")
    private UUID categoryId;


    @Field(name = "storeId")
    private UUID storeId;

    @Field(name = "categoryName")
    private String categoryName;

}
