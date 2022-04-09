package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import com.example.myRetail.repository.PricingRepository
import com.example.myRetail.repository.model.PricingData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductApiSpec extends Specification {
    static final String PRODUCT_ID = '13860428'

    @LocalServerPort
    int port

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    PricingRepository pricingRepository

    def "getProduct"() {
        when:
        Product result = restTemplate.getForObject("http://localhost:$port/product/$PRODUCT_ID", Product)

        then:
        result.id == PRODUCT_ID
    }

    def "getProduct - unknown product"() {
        when:
        ResponseEntity<Product> responseEntity = restTemplate.exchange("http://localhost:$port/product/99999", HttpMethod.GET, null, Product)

        then:
        responseEntity.statusCodeValue == 404
    }

    def "updatePrice"() {
        setup:
        PricingData initialPricing = pricingRepository.findPricingByTcn(PRODUCT_ID)

        when:
        ResponseEntity<Product> response = restTemplate.exchange("http://localhost:$port/product/$PRODUCT_ID", HttpMethod.PUT, new HttpEntity(new Product(id: PRODUCT_ID, name: 'name', currentPrice: 1.23, currencyCode: 'EUR')), Product)

        then:
        response.statusCodeValue == 200
        response.getBody().currentPrice == 1.23
        response.getBody().currencyCode == 'EUR'

        and:
        PricingData pricingData = pricingRepository.findPricingByTcn(PRODUCT_ID)
        pricingData.currentPrice == 1.23d
        pricingData.currencyCode == 'EUR'

        cleanup:
        pricingRepository.save(initialPricing)
    }

    def "updatePrice - request and path mismatch"() {
        when:
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:$port/product/99999", HttpMethod.PUT, new HttpEntity(new Product(id: PRODUCT_ID, name: 'name', currentPrice: 1.23, currencyCode: 'EUR')), Product)

        then:
        responseEntity.statusCodeValue == 400
    }

    def "updatePrice - unknown product"() {
        when:
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:$port/product/99999", HttpMethod.PUT, new HttpEntity(new Product(id: '99999', name: 'name', currentPrice: 1.23, currencyCode: 'EUR')), Product)

        then:
        responseEntity.statusCodeValue == 404
    }
}
