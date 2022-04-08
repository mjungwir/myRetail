package com.example.myRetail.service

import com.example.myRetail.domain.Product
import com.example.myRetail.mapper.ProductMapper
import com.example.myRetail.product.ProductApiResponseDto
import com.example.myRetail.product.ProductRestClient
import com.example.myRetail.repository.PricingRepository
import com.example.myRetail.repository.model.PricingData
import spock.lang.Specification

class ProductServiceSpec extends Specification {
    ProductService productService = new ProductService()
    PricingRepository mockPricingRepository = Mock()
    ProductMapper mockProductMapper = Mock()
    ProductRestClient mockProductRestClient = Mock()

    void setup() {
        productService.pricingRepository = mockPricingRepository
        productService.productMapper = mockProductMapper
        productService.productRestClient = mockProductRestClient
    }

    def "getProduct"() {
        setup:
        String tcin = 'tcin1'
        ProductApiResponseDto responseDto = Stub()
        Product partiallymappedProduct = Stub()
        PricingData pricingData = Stub()
        Product fullyMappedProduct = Stub()

        when:
        Product result = productService.getProduct(tcin)

        then:
        1 * mockProductRestClient.getProductData(tcin) >> responseDto
        1 * mockProductMapper.mapFromProductApi(responseDto) >> partiallymappedProduct
        1 * mockPricingRepository.findPricingByTcn(tcin) >> pricingData
        1 * mockProductMapper.mapFromPricingData(pricingData, partiallymappedProduct) >> fullyMappedProduct
        result == fullyMappedProduct
    }
}
