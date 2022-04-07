package com.example.myRetail.repository

import com.example.myRetail.model.PricingData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PricingRepository extends MongoRepository<PricingData, String> {
    @Query("{tcin:'?0'}")
    PricingData findPricingByTcn(String tcn)
}
