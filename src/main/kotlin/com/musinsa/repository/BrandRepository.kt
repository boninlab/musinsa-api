package com.musinsa.repository

import com.musinsa.domain.Brand
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Brand 엔티티에 대한 DB 접근을 처리하는 Repository
 */
interface BrandRepository : JpaRepository<Brand, Long> {

    /**
     * 브랜드 이름으로 브랜드 조회
     */
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Brand?
}
