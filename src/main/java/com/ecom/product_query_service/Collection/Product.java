package com.ecom.product_query_service.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@Document(collection = "products")
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Product {

    @Id
    @Field(name = "product_query_Id")
    private String product_query_Id;

    @Field(name = "product_id")
    private UUID productId;

    @Field(name = "store_id")
    private UUID storeId;

    @NotNull(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Field(name = "product_name")
    private String productName;


    @Field(name = "description")
    private String description;

    @Field(name = "tags")
    private List<String> tags;

    @Field(name = "custom_fields")
    private Map<String, String> customFields;

    @NotNull(message = "Price is required")
    @Field(name = "price")
    private BigDecimal price;

    @Field(name = "compare_at_price")
    private BigDecimal compareAtPrice;

    @Field(name = "quantity")
    private int quantity;

    @Field(name = "published")
    private Boolean published;

    @Field(name = "offer_val")
    private BigDecimal offerVal;

    @Field(name = "archived")
    private Boolean archived;

    @Field(name = "in_stock")
    private Boolean inStock;

    @Field(name = "product_image")
    private List<ProductImage> productImages;

    @Field(name = "categories")
    private List<Category> categories;

    @Field(name = "productVariants")
    private List<ProductVariant> productVariants;

    private LocalDateTime createDate;

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
