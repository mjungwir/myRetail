package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductApiSpec extends Specification {
    static final String PRODUCT_ID = '13860428'

    @LocalServerPort
    private int port

    @Autowired
    private TestRestTemplate restTemplate

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
}
