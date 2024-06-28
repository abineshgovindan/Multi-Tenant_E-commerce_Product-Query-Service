package com.ecom.product_query_service.Services.DBWriteServices;

import com.ecom.product_query_service.Collection.Category;
import com.ecom.product_query_service.Collection.Product;
import com.ecom.product_query_service.Collection.ProductImage;
import com.ecom.product_query_service.DTO.OperationDtos.CategoryDTO;
import com.ecom.product_query_service.DTO.OperationDtos.ProductDTO;
import com.ecom.product_query_service.DTO.OperationDtos.ProductImageDTO;
import com.ecom.product_query_service.DTO.OperationObject;
import com.ecom.product_query_service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWriteService {

    private final ProductRepository productRepository;

    private  LinkedHashMap<String, Object> operationMap =null;
    private List<Object> objects = null;
    private LinkedHashMap<String,Object> imageMap = null;
    private List<ProductImageDTO> images = null;
    private List<CategoryDTO> categories = null;
    private List<LinkedHashMap<String, Object>> imageMaps = null;
    private List<LinkedHashMap<String, Object>> categoryMaps = null;



    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public void processOperations(List<OperationObject> operations) {

        operations.forEach(operation -> {
            switch (operation.getOperationType()) {
                case "ADD_PRODUCTS":
                this.addProducts(operation.getOperationObject());
                    break;
                case "UPDATE_PRODUCTS":
                    updateProducts(operation.getOperationObject());
                    break;
                case "DELETE_PRODUCTS":
                    deleteProducts(operation.getOperationObject());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation type: " + operation.getOperationType());
            }
        });

    }

    private void deleteProducts(Object operationObject) {
    }

    private void updateProducts(Object operationObject) {
    }

    private void addProducts(Object operationObject) {
      //  System.out.println("Data  -> " + operationObject.toString());
    if(operationObject instanceof LinkedHashMap){
        operationMap = (LinkedHashMap<String, Object>) operationObject;
        ProductDTO productDTO = convertMapToProductDTO(operationMap);
        System.out.println("The product DTO "+productDTO.toString());
        try {
            Product product =  Product.builder()
                    .productId(productDTO.getProductId())
                    .storeId(productDTO.getStoreId())
                    .productName(productDTO.getProductName())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .compareAtPrice(productDTO.getCompareAtPrice())
                    .quantity(productDTO.getQuantity())
                    .published(productDTO.getPublished())
                    .offerVal(productDTO.getOfferVal())
                    .archived(productDTO.getArchived())
                    .inStock(productDTO.getInStock())
                    .tags(productDTO.getTags())
                    .customFields(productDTO.getCustomFields())
                    .productImages(productDTO.getImages().stream().map(images ->
                            ProductImage.builder()
                                    .productImageId(images.getProductImageId())
                                    .imageUrl(images.getImageUrl())
                                    .imagePosition(images.getImagePosition())
                                    .build()
                    ).collect(Collectors.toList()))
                    .categories(productDTO.getCategories().stream().map(category ->
                            Category.builder()
                                    .categoryId(category.getCategories_id())
                                    .storeId(category.getStoreId())
                                    .categoryName(category.getCategoryName())
                                    .build()
                    ).collect(Collectors.toList()))
                    .createDate(productDTO.getCreateDate())
                    .updateDate(productDTO.getUpdateDate())
                    .build();
            System.out.println("The Product -> "+ product.toString());
            productRepository.save(product);

        }catch (Exception e){
            System.out.println(e);
        }

    }


    }


    private ProductDTO convertMapToProductDTO(LinkedHashMap<String, Object> map){
        imageMaps = (List<LinkedHashMap<String, Object>>) map.get("images");
        images = imageMaps.stream().map(imageMap ->
                ProductImageDTO.builder()
                        .productImageId(UUID.fromString((String) imageMap.get("productImageId")))
                        .imageUrl((String) imageMap.get("imageUrl"))
                        .imagePosition((Integer) imageMap.get("imagePosition"))
                        .build()
        ).collect(Collectors.toList());

        categoryMaps = (List<LinkedHashMap<String, Object>>) map.get("categories");
        categories = categoryMaps.stream().map(categoryMap ->
                CategoryDTO.builder()
                        .categories_id(UUID.fromString((String) categoryMap.get("categories_id")))
                        .categoryName((String) categoryMap.get("categoryName"))
                        .storeId(UUID.fromString((String) categoryMap.get("categories_id")))
                        .build()
        ).collect(Collectors.toList());

//        objects = (List<Object>) map.get("images");
//        objects.forEach( object -> {
//            if (object instanceof LinkedHashMap){
//                imageMap = (LinkedHashMap<String,Object>)  object;
//                System.out.println( "productImageId --> " + UUID.fromString((String) imageMap.get("productImageId")));
//            }
//        });

        return ProductDTO.builder()
                .productId(UUID.fromString( (String)map.get("productId")))
                .storeId(UUID.fromString((String) map.get("storeId")))
                .productName((String) map.get("productName"))
                .description((String) map.get("description"))
                .price(convertToBigDecimal(map.get("price")))
                .compareAtPrice(convertToBigDecimal(map.get("compareAtPrice")))
                .quantity((Integer) map.get("quantity"))
                .published((Boolean) map.get("published"))
                .offerVal(convertToBigDecimal(map.get("offerVal")))
                .archived((Boolean) map.get("archived"))
                .inStock((Boolean) map.get("inStock"))
                .tags((List<String>) map.get("tags"))
                .customFields((Map<String, String>) map.get("customFields"))
                .images(images)
                .categories(categories)
                .createDate(parseDateTimeArray((List<Integer>) map.get("createDate")))
                .updateDate(parseDateTimeArray((List<Integer>) map.get("updateDate")))
                .build();
    }

    private BigDecimal convertToBigDecimal(Object value) {
        if (value instanceof Integer) {
            return BigDecimal.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return BigDecimal.valueOf((Long) value);
        } else if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        } else if (value instanceof String) {
            return new BigDecimal((String) value);
        } else {
            return (BigDecimal) value;
        }
    }

    private LocalDateTime parseDateTimeArray(List<Integer> dateTimeArray) {
        if (dateTimeArray.size() != 7) {
            throw new IllegalArgumentException("The date time array must have exactly 7 elements.");
        }
        return LocalDateTime.of(
                dateTimeArray.get(0), // year
                dateTimeArray.get(1), // month
                dateTimeArray.get(2), // day
                dateTimeArray.get(3), // hour
                dateTimeArray.get(4), // minute
                dateTimeArray.get(5), // second
                dateTimeArray.get(6)  // nanosecond
        );
    }
}



