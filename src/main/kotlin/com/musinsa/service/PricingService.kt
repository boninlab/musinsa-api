package com.musinsa.service

import com.musinsa.common.toPriceString
import com.musinsa.domain.Category
import com.musinsa.dto.*
import com.musinsa.repository.BrandProductRepository
import com.musinsa.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class PricingService(
    private val brandProductRepository: BrandProductRepository,
    private val brandRepository: BrandRepository
) {

    /**
     * 카테고리별 최저가 브랜드 목록과 총액을 조회한다.
     * 각 카테고리에 대해 가장 저렴한 상품을 가진 브랜드를 찾아,
     * 카테고리명, 브랜드명, 가격 정보를 포함한 리스트로 반환한다.
     */
    fun getLowestPerCategoryDto(): LowestByCategoryResponse {
        val allProducts = brandProductRepository.findAll()

        val items = Category.entries.map { category ->
            val cheapest = allProducts.filter { it.category == category }
                .minByOrNull { it.price }

            CategoryLowestPriceDto(
                카테고리 = category.name,
                브랜드 = cheapest?.brand?.name ?: "없음",
                가격 = (cheapest?.price ?: 0).toPriceString()
            )
        }

        val total = items.sumOf {
            it.가격.replace(",", "").toInt()
        }

        return LowestByCategoryResponse(
            items = items,
            총액 = total.toPriceString()
        )
    }


    /**
     * 모든 카테고리를 포함한 단일 브랜드 중 최저가 브랜드를 조회한다.
     * 해당 브랜드의 카테고리별 가격과 총액을 함께 반환한다.
     */
    fun getCheapestSingleBrandDto(): SingleBrandLowestInfo {
        val allBrands = brandRepository.findAll()
        val allProducts = brandProductRepository.findAll()

        val brandTotalMap = allBrands.mapNotNull { brand ->
            val products = allProducts.filter { it.brand == brand }
            if (products.map { it.category }.toSet() == Category.entries.toSet()) {
                val total = products.sumOf { it.price }
                Triple(brand.name, total, products)
            } else null
        }

        val cheapest = brandTotalMap.minByOrNull { it.second }
            ?: throw IllegalStateException("조건을 만족하는 브랜드가 없습니다")

        return SingleBrandLowestInfo(
            브랜드 = cheapest.first,
            카테고리 = cheapest.third.map {
                CategoryPriceInfoDto(
                    카테고리 = it.category.name,
                    가격 = it.price.toPriceString()
                )
            },
            총액 = cheapest.second.toPriceString()
        )
    }

    /**
     * 특정 카테고리에 대해 최저가 및 최고가 브랜드를 조회한다.
     */
    fun getMinMaxByCategoryDto(category: Category): CategoryMinMaxResponse {
        val products = brandProductRepository.findByCategory(category)

        val minPrice = products.minByOrNull { it.price }?.price
        val maxPrice = products.maxByOrNull { it.price }?.price

        val minList = products.filter { it.price == minPrice }
            .map {
                BrandPriceDto(
                    브랜드 = it.brand.name,
                    가격 = it.price.toPriceString()
                )
            }

        val maxList = products.filter { it.price == maxPrice }
            .map {
                BrandPriceDto(
                    브랜드 = it.brand.name,
                    가격 = it.price.toPriceString()
                )
            }

        return CategoryMinMaxResponse(
            카테고리 = category.name,
            최저가 = minList,
            최고가 = maxList
        )
    }
}
