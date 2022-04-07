package com.example.myRetail.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("pricing_data")
class PricingData {

    @Id
    String tcin
    double currentPrice
    String currencyCode
}
