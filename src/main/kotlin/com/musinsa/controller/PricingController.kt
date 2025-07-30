package com.musinsa.controller

import com.musinsa.domain.Category
import com.musinsa.service.PricingService
import org.springframework.web.bind.annotation.*

/**
 * 가격 관련 조회 기능을 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/api/pricing")
class PricingController(
    private val pricingService: PricingService
) {

    /**
     * 카테고리별 최저가 브랜드와 가격, 총액을 반환하는 API
     */
    @GetMapping("/lowest-by-category")
    fun getLowestByCategory(): Map<String, Any> {
        val result = pricingService.getLowestPerCategory()

        val items = result.map { (category, pair) ->
            mapOf(
                "카테고리" to category.name,
                "브랜드" to pair.first,
                "가격" to pair.second
            )
        }

        val total = result.values.sumOf { it.second }

        return mapOf(
            "items" to items,
            "총액" to total
        )
    }
}
