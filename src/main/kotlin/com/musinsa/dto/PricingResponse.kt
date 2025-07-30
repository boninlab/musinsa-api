package com.musinsa.dto

data class CategoryLowestPriceDto(
    val 카테고리: String,
    val 브랜드: String,
    val 가격: String
)

data class LowestByCategoryResponse(
    val items: List<CategoryLowestPriceDto>,
    val 총액: String
)

data class CategoryPriceInfoDto(
    val 카테고리: String,
    val 가격: String
)

data class SingleBrandLowestResponse(
    val 최저가: SingleBrandLowestInfo
)

data class SingleBrandLowestInfo(
    val 브랜드: String,
    val 카테고리: List<CategoryPriceInfoDto>,
    val 총액: String
)

data class CategoryMinMaxResponse(
    val 카테고리: String,
    val 최저가: List<BrandPriceDto>,
    val 최고가: List<BrandPriceDto>
)

data class BrandPriceDto(
    val 브랜드: String,
    val 가격: String
)