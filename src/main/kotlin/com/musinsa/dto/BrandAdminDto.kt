package com.musinsa.dto

import com.musinsa.domain.Category

data class CreateBrandRequest(
    val name: String
)

data class CreateProductRequest(
    val brandName: String,
    val category: Category,
    val price: Int
)

data class UpdateProductRequest(
    val productId: Long,
    val price: Int
)
