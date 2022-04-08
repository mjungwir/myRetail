package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import com.example.myRetail.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @Autowired
    ProductService productService

    Product getProduct(String id) {
        return productService.getProduct(id)
    }
}
