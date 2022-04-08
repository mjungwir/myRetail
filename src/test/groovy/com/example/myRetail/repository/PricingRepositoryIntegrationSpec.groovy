package com.example.myRetail.repository

import com.example.myRetail.repository.model.PricingData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PricingRepositoryIntegrationSpec extends Specification {

    @Autowired
    PricingRepository pricingRepository

    def "findPricingByTcn"() {
        setup:
        PricingData pricingData = pricingRepository.save(new PricingData(tcin: 'bob', currentPrice: 1.23, currencyCode: 'USD'))

        when:
        PricingData result = pricingRepository.findPricingByTcn('bob')

        then:
        result
        result.tcin == pricingData.tcin
        result.currentPrice == pricingData.currentPrice
        result.currencyCode == pricingData.currencyCode

        cleanup:
        pricingRepository.delete(pricingData)
    }

    def "findPricingByTcn - not found"() {
        when:
        PricingData result = pricingRepository.findPricingByTcn('bob')

        then:
        result == null
    }
}
