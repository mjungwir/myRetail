package com.example.myRetail.mapper

import com.example.myRetail.domain.Product
import com.example.myRetail.product.ProductApiResponseDto
import com.example.myRetail.repository.model.PricingData

class ProductMapper {
    Product mapFromProductApi(ProductApiResponseDto productApiResponseDto) {
        return productApiResponseDto?.data?.product?.with {
            return new Product(id: tcin, name: item.productDescription.title)
        }
    }

    Product mapFromPricingData(PricingData pricingData, Product existingProduct) {
        return pricingData.with {
            existingProduct.currentPrice = currentPrice
            existingProduct.currencyCode = currencyCode
            return existingProduct
        }
    }
}
