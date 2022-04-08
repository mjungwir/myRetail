package com.example.myRetail.mapper

import com.example.myRetail.domain.Product
import com.example.myRetail.product.ProductApiResponseDto
import com.example.myRetail.repository.model.PricingData
import spock.lang.Specification

class ProductMapperSpec extends Specification {
    ProductMapper productMapper = new ProductMapper()

    def "mapFromProductApi"() {
        setup:
        String tcin = 'tcin1'
        String title = 'product name'
        ProductApiResponseDto productApiResponseDto = new ProductApiResponseDto(data: new ProductApiResponseDto.DataDto(product: new ProductApiResponseDto.ProductDto(tcin: tcin, item: new ProductApiResponseDto.ItemDto(productDescription: new ProductApiResponseDto.ProductDescriptionDto(title: title)))))

        when:
        Product product = productMapper.mapFromProductApi(productApiResponseDto)

        then:
        product.id == tcin
        product.name == title
        product.currencyCode == null
        product.currentPrice == null
    }

    def "mapFromPricingData"() {
        setup:
        String tcin = 'tcin1'
        String title = 'product name'
        double currentPrice = 1.23
        String currencyCode = 'USD'
        PricingData pricingData = new PricingData(currentPrice: currentPrice, currencyCode: currencyCode)
        Product existingProduct = new Product(id: tcin, name: title)

        when:
        Product product = productMapper.mapFromPricingData(pricingData, existingProduct)

        then:
        product.id == tcin
        product.name == title
        product.currencyCode == currencyCode
        product.currentPrice == currentPrice
    }
}
