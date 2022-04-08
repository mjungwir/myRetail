package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import com.example.myRetail.service.ProductService
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
}
