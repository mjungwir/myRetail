package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import com.example.myRetail.service.ProductService
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class ProductControllerSpec extends Specification {
    ProductController productController = new ProductController()
    ProductService mockProductService = Mock()

    void setup() {
        productController.productService = mockProductService
    }

    def "getProduct"() {
        setup:
        Product product = Stub()

        when:
        Product result = productController.getProduct('123')

        then:
        1 * mockProductService.getProduct('123') >> product
        result == product
    }

    def "getProduct - not found"() {
        when:
        productController.getProduct('123')

        then:
        1 * mockProductService.getProduct('123') >> null
        ResponseStatusException e = thrown(ResponseStatusException)
        e.rawStatusCode == 404
    }

    def "updateProduct"() {
        setup:
        String id = '123'
        Product requestProduct = Stub() {
            getId() >> id
        }
        Product expectedProduct = Stub()

        when:
        Product result = productController.updateProductPricing(id, requestProduct)

        then:
        1 * mockProductService.updateProductPricing(requestProduct) >> expectedProduct
        result == expectedProduct
    }

    def "updateProduct - not found"() {
        setup:
        String id = '123'
        Product requestProduct = Stub() {
            getId() >> id
        }

        when:
        productController.updateProductPricing(id, requestProduct)

        then:
        1 * mockProductService.updateProductPricing(requestProduct) >> null
        ResponseStatusException e = thrown(ResponseStatusException)
        e.rawStatusCode == 404
    }

    def "updateProduct - path and request body mismatch"() {
        setup:
        String id = '123'
        Product requestProduct = Stub() {
            getId() >> '456'
        }

        when:
        productController.updateProductPricing(id, requestProduct)

        then:
        0 * _
        ResponseStatusException e = thrown(ResponseStatusException)
        e.rawStatusCode == 400
    }
}
