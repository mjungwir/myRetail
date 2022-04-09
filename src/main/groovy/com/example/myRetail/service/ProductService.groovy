package com.example.myRetail.service

import com.example.myRetail.domain.Product
import com.example.myRetail.mapper.ProductMapper
import com.example.myRetail.product.ProductApiResponseDto
import com.example.myRetail.product.ProductRestClient
import com.example.myRetail.repository.PricingRepository
import com.example.myRetail.repository.model.PricingData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {
    @Autowired
    PricingRepository pricingRepository

    @Autowired
    ProductMapper productMapper

    @Autowired
    ProductRestClient productRestClient

    Product getProduct(String tcin) {
        final ProductApiResponseDto responseDto = productRestClient.getProductData(tcin)
        if (!responseDto) {
            return null
        }

        Product product = productMapper.mapFromProductApi(responseDto)
        PricingData pricingData = pricingRepository.findPricingByTcn(tcin)
        return productMapper.mapFromPricingData(pricingData, product)
    }

    Product updateProductPricing(Product requestProduct) {
        final PricingData pricingData = pricingRepository.findPricingByTcn(requestProduct.id)
        if (!pricingData) {
            return null
        }

        pricingData.currentPrice = requestProduct.currentPrice
        pricingData.currencyCode = requestProduct.currencyCode
        pricingRepository.save(pricingData)

        return requestProduct
    }
}
