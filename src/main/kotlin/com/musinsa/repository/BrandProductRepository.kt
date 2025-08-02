package com.musinsa.repository

import com.musinsa.domain.Brand
import com.musinsa.domain.BrandProduct
import com.musinsa.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * BrandProduct 엔티티에 대한 DB 접근을 처리하는 Repository
 */
interface BrandProductRepository : JpaRepository<BrandProduct, Long> {

    /**
     * 특정 카테고리 상품들 조회
     */
    fun findByCategory(category: Category): List<BrandProduct>
    fun existsByBrandAndCategory(brand: Brand, category: Category): Boolean

    /**
     * 카테고리별 min 가격만 가져오는 쿼리를 사용하여 성능 최적화/필요한 데이터만 가져옴
     */
    @Query(
        """
        SELECT bp FROM BrandProduct bp
        WHERE bp.price = (
            SELECT MIN(bp2.price)
            FROM BrandProduct bp2
            WHERE bp2.category = bp.category
        )
    """
    )
    fun findCheapestProductPerCategory(): List<BrandProduct>

    /**
     * 모든 카테고리를 보유한 브랜드만 선별 + 총합 가격 계산
     */
    @Query(
        """
        SELECT bp.brand_id, SUM(bp.price) AS total_price
        FROM brand_products bp
        GROUP BY bp.brand_id
        HAVING COUNT(DISTINCT bp.category) = :categorySize
        ORDER BY total_price ASC
        LIMIT 1
        """,
        nativeQuery = true
    )
    fun findCheapestBrandWithAllCategories(@Param("categorySize") categorySize: Int): Map<String, Any>?

    @Query("SELECT bp FROM BrandProduct bp WHERE bp.brand.id = :brandId")
    fun findAllByBrandId(@Param("brandId") brandId: Long): List<BrandProduct>
}
