package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
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
}
