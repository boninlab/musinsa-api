package com.musinsa.service

import com.musinsa.domain.Brand
import com.musinsa.domain.BrandProduct
import com.musinsa.dto.BrandProductResponseDto
import com.musinsa.dto.CreateBrandRequest
import com.musinsa.dto.CreateProductRequest
import com.musinsa.dto.UpdateProductRequest
import com.musinsa.repository.BrandProductRepository
import com.musinsa.repository.BrandRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val brandRepository: BrandRepository,
    private val brandProductRepository: BrandProductRepository
) {

    /**
     * 브랜드를 등록한다. 동일한 이름이 존재하면 예외 발생.
     */
    fun createBrand(req: CreateBrandRequest): Brand {
        if (brandRepository.existsByName(req.name)) {
            throw IllegalArgumentException("이미 존재하는 브랜드입니다: ${req.name}")
        }
        return brandRepository.save(Brand(name = req.name))
    }

    /**
     * 브랜드에 상품을 추가한다.
     */
    fun createProduct(req: CreateProductRequest): BrandProductResponseDto {
        val brand = brandRepository.findByName(req.brandName)
            ?: throw IllegalArgumentException("브랜드가 존재하지 않습니다: ${req.brandName}")

        val exists = brandProductRepository.existsByBrandAndCategory(brand, req.category)
        if (exists) {
            throw IllegalArgumentException("브랜드 '${req.brandName}'는 이미 카테고리 '${req.category}'의 상품을 가지고 있습니다.")
        }

        val product = BrandProduct(
            brand = brand,
            category = req.category,
            price = req.price
        )

        val saved = brandProductRepository.save(product)

        return BrandProductResponseDto(
            id = saved.id,
            brandName = brand.name,
            category = saved.category,
            price = saved.price
        )
    }


    /**
     * 상품의 가격을 수정한다.
     */
    @Transactional
    fun updateProduct(req: UpdateProductRequest): BrandProductResponseDto {
        val product = brandProductRepository.findById(req.productId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 상품입니다") }

        product.price = req.price
        return BrandProductResponseDto(
            id = product.id,
            brandName = product.brand.name,
            category = product.category,
            price = product.price
        )
    }

    /**
     * 상품을 삭제한다.
     */
    fun deleteProduct(productId: Long) {
        if (!brandProductRepository.existsById(productId)) {
            throw IllegalArgumentException("존재하지 않는 상품입니다")
        }
        brandProductRepository.deleteById(productId)
    }
}
