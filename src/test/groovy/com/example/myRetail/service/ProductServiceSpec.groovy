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

    def "getProduct - not found"() {
        setup:
        String tcin = 'tcin1'

        when:
        Product result = productService.getProduct(tcin)

        then:
        1 * mockProductRestClient.getProductData(tcin) >> null
        0 * _

        and:
        result == null
    }

    def "updateProductPricing"() {
        setup:
        String tcin = 'tcin1'
        Product requestProduct = new Product(id: tcin, name: 'name', currentPrice: 4.56, currencyCode: 'EUR')
        PricingData existingPricingData = new PricingData(tcin: tcin, currentPrice: 1.23, currencyCode: 'USD')
        PricingData updatedPricingData = new PricingData(tcin: tcin, currentPrice: 4.56, currencyCode: 'EUR')
        Product fullyMappedProduct = Stub()

        when:
        Product result = productService.updateProductPricing(requestProduct)

        then:
        1 * mockPricingRepository.findPricingByTcn(tcin) >> existingPricingData
        1 * mockPricingRepository.save({it.tcin == tcin && it.currentPrice == 4.56 && it.currencyCode == 'EUR'}) >> updatedPricingData
        result == requestProduct
    }

    def "updateProductPricing - not found"() {
        setup:
        String tcin = 'tcin1'
        Product requestProduct = new Product(id: tcin, name: 'name', currentPrice: 4.56, currencyCode: 'EUR')

        when:
        Product result = productService.updateProductPricing(requestProduct)

        then:
        1 * mockPricingRepository.findPricingByTcn(tcin) >> null
        0 * _

        and:
        result == null
    }
}
