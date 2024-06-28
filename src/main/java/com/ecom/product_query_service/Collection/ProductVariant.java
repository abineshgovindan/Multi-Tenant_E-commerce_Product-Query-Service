package com.ecom.product_query_service.Collection;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariant {

    @Field(name = "variantId")
    private UUID variantId;

    @Field(name = "sku")
    private String sku;

    @Field(name = "variant_name")
    private String variantName;

    @Field(name = "variant_price")
    private BigDecimal variantPrice;

    @Field(name = "compareAtPrice")
    private BigDecimal compareAtPrice;

    @Field(name = "tags")
    private List<String> tags;

    @Field(name = "options")
    private Map<String, String> options;

    @Field(name = "quantity")
    private int quantity;

    @Field(name = "archived")
    private Boolean archived;

    @Field(name = "inStock")
    private Boolean inStock;

    @Field(name = "variantImages")
    private List<ProductImage> variantImages;

    @Field(name = "categories")
    private List<Category> categories;



    @Field(name = "createDate")
    private LocalDateTime createDate;

    @Field(name = "updateDate")
    private LocalDateTime updateDate;


    @PrePersist
    public void onPrePersist() {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updateDate = LocalDateTime.now();
    }

}
