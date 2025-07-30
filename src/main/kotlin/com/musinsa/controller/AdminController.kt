package com.musinsa.controller

import com.musinsa.dto.CreateBrandRequest
import com.musinsa.dto.CreateProductRequest
import com.musinsa.dto.UpdateProductRequest
import com.musinsa.service.AdminService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminService: AdminService
) {

    /**
     * 브랜드 등록
     */
    @PostMapping("/brands")
    fun createBrand(@RequestBody req: CreateBrandRequest) =
        adminService.createBrand(req)

    /**
     * 상품 등록
     */
    @PostMapping("/products")
    fun createProduct(@RequestBody req: CreateProductRequest) =
        adminService.createProduct(req)

    /**
     * 상품 가격 수정
     */
    @PutMapping("/products")
    fun updateProduct(@RequestBody req: UpdateProductRequest) =
        adminService.updateProduct(req)

    /**
     * 상품 삭제
     */
    @DeleteMapping("/products/{productId}")
    fun deleteProduct(@PathVariable productId: Long) =
        adminService.deleteProduct(productId)
}
