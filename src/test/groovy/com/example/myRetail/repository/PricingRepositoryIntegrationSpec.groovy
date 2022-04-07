package com.example.myRetail.repository

import com.example.myRetail.model.PricingData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PricingRepositoryIntegrationSpec extends Specification {

    @Autowired
    PricingRepository pricingRepository

    def "findPricingByTcn"() {
        setup:
        pricingRepository.save(new PricingData(tcin: 'bob', currentPrice: 1.23, currencyCode: 'USD'))

        when:
        PricingData result = pricingRepository.findPricingByTcn('bob')

        then:
        result
        result.tcin == 'bob'
        result.currentPrice == 1.23 as double
        result.currencyCode == 'USD'
    }
}
