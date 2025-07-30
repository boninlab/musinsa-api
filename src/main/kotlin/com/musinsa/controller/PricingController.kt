package com.musinsa.controller

import com.musinsa.domain.Category
import com.musinsa.dto.CategoryMinMaxResponse
import com.musinsa.dto.LowestByCategoryResponse
import com.musinsa.dto.SingleBrandLowestResponse
import com.musinsa.service.PricingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/pricing")
class PricingController(
    private val pricingService: PricingService
) {

    /**
     * 1. 카테고리별 최저가 브랜드 조회
     */
    @GetMapping("/lowest-by-category")
    fun getLowestByCategory(): LowestByCategoryResponse {
        return pricingService.getLowestPerCategoryDto()
    }


    /**
     * 2. 단일 브랜드 전체 카테고리 최저가 조회
     */
    @GetMapping("/lowest-single-brand")
    fun getCheapestSingleBrand(): SingleBrandLowestResponse {
        val result = pricingService.getCheapestSingleBrandDto()
        return SingleBrandLowestResponse(최저가 = result)
    }

    /**
     * 3. 카테고리별 최고가/최저가 브랜드 조회
     */
    @GetMapping("/min-max")
    fun getMinMaxByCategory(@RequestParam category: String): CategoryMinMaxResponse {
        val parsed = runCatching { Category.valueOf(category) }
        return parsed.getOrElse {
            throw IllegalArgumentException("유효하지 않은 카테고리입니다.")
        }.let {
            pricingService.getMinMaxByCategoryDto(it)
        }
    }
}
