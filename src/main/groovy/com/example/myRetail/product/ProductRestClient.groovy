package com.example.myRetail.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductRestClient {
    @Autowired
    RestTemplate restTemplate

    @Value('${productRestClient.uri}')
    String productUri

    ProductApiResponseDto getProductData(String tcin) {
        return restTemplate.getForObject("$productUri$tcin", ProductApiResponseDto)
    }
}
