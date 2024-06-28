package com.ecom.product_query_service.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductImage {
    @Field(name = "product_image_id")
    private UUID productImageId;


    @Field(name = "image_url")
    private String imageUrl;

    @Field(name = "image_position")
    private Integer imagePosition;

}
