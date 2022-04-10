package com.example.myRetail.repository.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("pricing_data")
class PricingData {

    @Id
    String id
    String tcin
    double currentPrice
    String currencyCode
}
