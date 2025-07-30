package com.musinsa.service

import com.musinsa.domain.Category
import com.musinsa.repository.BrandProductRepository
import org.springframework.stereotype.Service

/**
 * 가격 계산 및 상품 조회 비즈니스 로직을 담당하는 서비스
 */
@Service
class PricingService(
    private val brandProductRepository: BrandProductRepository
) {

    /**
     * 카테고리별로 최저가 상품의 브랜드와 가격을 조회하는 메서드
     * @return Map<Category, Pair<브랜드명, 가격>>
     */
    fun getLowestPerCategory(): Map<Category, Pair<String, Int>> {
        val allProducts = brandProductRepository.findAll()

        return Category.values().associateWith { category ->
            allProducts
                .filter { it.category == category }
                .minByOrNull { it.price }
                ?.let { it.brand.name to it.price }
                ?: ("없음" to 0)
        }
    }
}
