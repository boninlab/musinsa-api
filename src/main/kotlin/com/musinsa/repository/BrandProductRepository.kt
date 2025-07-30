package com.musinsa.repository

import com.musinsa.domain.Brand
import com.musinsa.domain.BrandProduct
import com.musinsa.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

/**
 * BrandProduct 엔티티에 대한 DB 접근을 처리하는 Repository
 */
interface BrandProductRepository : JpaRepository<BrandProduct, Long> {

    /**
     * 특정 카테고리 상품들 조회
     */
    fun findByCategory(category: Category): List<BrandProduct>
    fun existsByBrandAndCategory(brand: Brand, category: Category): Boolean
}
