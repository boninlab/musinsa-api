package com.musinsa.service

import com.musinsa.common.toPriceString
import com.musinsa.domain.Brand
import com.musinsa.domain.BrandProduct
import com.musinsa.domain.Category
import com.musinsa.repository.BrandProductRepository
import com.musinsa.repository.BrandRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * PricingService 단위 테스트
 */
class PricingServiceTest {

    private val brandRepo = mockk<BrandRepository>()
    private val productRepo = mockk<BrandProductRepository>()
    private val service = PricingService(productRepo, brandRepo)

    @Test
    fun `getLowestPerCategoryDto - 각 카테고리 최저가와 총액 계산`() {
        val brand1 = Brand(name = "A")
        val brand2 = Brand(name = "B")

        val products = listOf(
            BrandProduct(brand = brand1, category = Category.상의, price = 1000),
            BrandProduct(brand = brand2, category = Category.상의, price = 1500),
            BrandProduct(brand = brand1, category = Category.바지, price = 2000),
            BrandProduct(brand = brand2, category = Category.바지, price = 1000)
        )

        every { productRepo.findAll() } returns products

        val result = service.getLowestPerCategoryDto()

        assertEquals("2,000", result.총액)
        assertTrue(result.items.any { it.카테고리 == "상의" && it.브랜드 == "A" && it.가격 == "1,000" })
        assertTrue(result.items.any { it.카테고리 == "바지" && it.브랜드 == "B" && it.가격 == "1,000" })
    }

    @Test
    fun `getCheapestSingleBrandDto - 모든 카테고리 가진 브랜드 중 최저 총액 브랜드 반환`() {
        val brand = Brand(name = "A")

        val products = Category.entries.mapIndexed { idx, category ->
            BrandProduct(
                brand = brand,
                category = category,
                price = 1000 + idx * 100 // 1000, 1100, ..., 증가
            )
        }

        every { brandRepo.findAll() } returns listOf(brand)
        every { productRepo.findAll() } returns products

        val result = service.getCheapestSingleBrandDto()

        assertEquals("A", result.브랜드)
        assertEquals(products.sumOf { it.price }.toPriceString(), result.총액)
        assertEquals(Category.entries.size, result.카테고리.size)
    }


    @Test
    fun `getMinMaxByCategoryDto - 특정 카테고리 최저가 및 최고가 브랜드 리스트 반환`() {
        val brand1 = Brand(name = "A")
        val brand2 = Brand(name = "B")
        val category = Category.가방

        val products = listOf(
            BrandProduct(brand = brand1, category = category, price = 1000),
            BrandProduct(brand = brand2, category = category, price = 3000)
        )

        every { productRepo.findByCategory(category) } returns products

        val result = service.getMinMaxByCategoryDto(category)

        assertEquals("가방", result.카테고리)
        assertEquals(1, result.최저가.size)
        assertEquals("A", result.최저가[0].브랜드)
        assertEquals("1,000", result.최저가[0].가격)
        assertEquals("3,000", result.최고가[0].가격)
    }
}
