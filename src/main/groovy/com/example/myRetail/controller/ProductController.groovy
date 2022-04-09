package com.example.myRetail.controller

import com.example.myRetail.domain.Product
import com.example.myRetail.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class ProductController {

    @Autowired
    ProductService productService

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable('id') String id) {
        final Product product = productService.getProduct(id)
        if (!product) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return product
    }

    @PutMapping("/product/{id}")
    Product updateProductPricing(@PathVariable('id') String id, @RequestBody Product requestProduct) {
        if (id != requestProduct.id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        final Product product = productService.updateProductPricing(requestProduct)
        if (!product) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return product
    }
}
