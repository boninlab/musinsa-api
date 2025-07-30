package com.musinsa.dto

import com.musinsa.domain.Category

data class BrandProductResponseDto(
    val id: Long,
    val brandName: String,
    val category: Category,
    val price: Int
)
