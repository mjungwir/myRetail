package com.example.myRetail.product

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ProductRestClientSpec extends Specification {
    static final String PRODUCT_URI = 'https://product.com?tcin='

    ProductRestClient productRestClient = new ProductRestClient()
    RestTemplate mockRestTemplate = Mock()

    void setup() {
        productRestClient.restTemplate = mockRestTemplate
        productRestClient.productUri = PRODUCT_URI
    }

    def "getProductData"() {
        setup:
        ProductApiResponseDto stubResponse = Stub()

        when:
        ProductApiResponseDto result = productRestClient.getProductData('123')

        then:
        1 * mockRestTemplate.getForObject("${PRODUCT_URI}123", ProductApiResponseDto) >> stubResponse
        result == stubResponse
    }

    def "getProductData - product not found"() {
        when:
        ProductApiResponseDto result = productRestClient.getProductData('123')

        then:
        1 * mockRestTemplate.getForObject("${PRODUCT_URI}123", ProductApiResponseDto) >> { throw HttpClientErrorException.create('No product found with tcin 99999', HttpStatus.NOT_FOUND, null, null, null, null) }
        result == null
    }
}
