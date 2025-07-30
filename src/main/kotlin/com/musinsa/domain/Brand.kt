package com.musinsa.domain

import jakarta.persistence.*

/**
 * 브랜드 정보를 나타내는 엔티티
 */
@Entity
@Table(name = "brands")
class Brand(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0, // 브랜드 고유 ID

    @Column(unique = true, nullable = false)
    val name: String, // 브랜드 이름

    @OneToMany(mappedBy = "brand", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: MutableList<BrandProduct> = mutableListOf() // 브랜드의 상품 리스트
)
