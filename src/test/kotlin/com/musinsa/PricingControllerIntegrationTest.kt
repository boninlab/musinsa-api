package com.musinsa.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestTemplate

/**
 * 실제 Spring Boot 환경에서 PricingController API 통합 테스트
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricingControllerIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    private val restTemplate = RestTemplate()

    @Test
    fun `GET lowest-by-category should return 200 and valid body`() {
        val url = "http://localhost:$port/api/pricing/lowest-by-category"
        val response = restTemplate.getForEntity(url, String::class.java)

        assertThat(response.statusCode.value()).isEqualTo(200)
        assertThat(response.body).contains("카테고리")
        assertThat(response.body).contains("브랜드")
        assertThat(response.body).contains("가격")
        assertThat(response.body).contains("총액")
    }

    @Test
    fun `GET lowest-single-brand should return 200 and include brand`() {
        val url = "http://localhost:$port/api/pricing/lowest-single-brand"
        val response = restTemplate.getForEntity(url, String::class.java)

        assertThat(response.statusCode.value()).isEqualTo(200)
        assertThat(response.body).contains("브랜드")
        assertThat(response.body).contains("카테고리")
        assertThat(response.body).contains("총액")
    }

    @Test
    fun `GET min-max with valid category should return 200`() {
        val category = "상의"
        val url = "http://localhost:$port/api/pricing/min-max?category=$category"
        val response = restTemplate.getForEntity(url, String::class.java)

        assertThat(response.statusCode.value()).isEqualTo(200)
        assertThat(response.body).contains("카테고리")
        assertThat(response.body).contains("최저가")
        assertThat(response.body).contains("최고가")
    }

    @Test
    fun `GET min-max with invalid category should return 400`() {
        val category = "없는카테고리"
        val url = "http://localhost:$port/api/pricing/min-max?category=$category"

        val ex =
            org.junit.jupiter.api.assertThrows<org.springframework.web.client.HttpClientErrorException.BadRequest> {
                restTemplate.getForEntity(url, String::class.java)
            }

        assertThat(ex.statusCode.value()).isEqualTo(400)
    }
}
