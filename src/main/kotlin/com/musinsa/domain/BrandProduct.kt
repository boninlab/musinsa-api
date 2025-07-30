package com.musinsa.domain

import jakarta.persistence.*

/**
 * 브랜드별 카테고리 상품 가격 정보를 나타내는 엔티티
 */
@Entity
@Table(
    name = "brand_products",
    uniqueConstraints = [UniqueConstraint(columnNames = ["brand_id", "category"])]
)
class BrandProduct(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0, // 상품 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand, // 소속 브랜드

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: Category, // 카테고리

    @Column(nullable = false)
    var price: Int // 가격 (배송비 제외)
)
