package com.example.myRetail.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ProductApiResponseDto {
    DataDto data

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class DataDto {
        ProductDto product
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ProductDto {
        String tcin
        ItemDto item
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ItemDto {
        @JsonProperty('product_description')
        ProductDescriptionDto productDescription
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ProductDescriptionDto {
        String title
    }
}



