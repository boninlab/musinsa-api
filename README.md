# Musinsa Backend API (Kotlin + Spring Boot)

무신사 백엔드 과제로, 운영자가 브랜드 및 상품을 관리하고  
최저가/최고가 브랜드 정보를 조회할 수 있는 API를 구현한 프로젝트입니다.

---

## 구현 범위

### 관리자 기능 (POST / PUT / DELETE)

- 새로운 브랜드 등록 (중복 방지)
- 브랜드별 상품 등록 (중복 카테고리 방지)
- 상품 가격 수정
- 상품 삭제

### 사용자 조회 기능 (GET)

1. `/api/pricing/lowest-by-category`
    - 카테고리별 최저가 브랜드 목록 조회

2. `/api/pricing/lowest-single-brand`
    - 모든 카테고리를 갖춘 단일 브랜드 중 최저 총액 브랜드 조회

3. `/api/pricing/min-max?category=상의`
    - 특정 카테고리의 최고가/최저가 브랜드 조회

---

## 실행 방법

### 1. 요구사항

- Java 17 이상
- Gradle (wrapper 포함)
- Kotlin 1.9+
- Spring Boot 3.5.4

### 2. 실행 방법

```bash
# 빌드
./gradlew clean build

# 실행
./gradlew bootRun
```

> 서버는 기본적으로 `http://localhost:8080`에서 구동됩니다.

---

## 예시 CURL 요청

### 1. 브랜드 등록

```bash
curl -X POST http://localhost:8080/api/admin/brands \
  -H "Content-Type: application/json" \
  -d '{"name": "Z"}'
```

---

### 2. 상품 등록

```bash
curl -X POST http://localhost:8080/api/admin/products \
  -H "Content-Type: application/json" \
  -d '{
    "brandName": "Z",
    "category": "아우터",
    "price": 19900
}'
```

---

### 3. 상품 수정

```bash
curl -X PUT http://localhost:8080/api/admin/products \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "price": 24900
}'
```

---

### 4. 상품 삭제

```bash
curl -X DELETE http://localhost:8080/api/admin/products/1
```

---

### 5. 카테고리별 최저가 브랜드 목록 조회

```bash
curl http://localhost:8080/api/pricing/lowest-by-category
```

---

### 6. 단일 브랜드 최저 총액 조회

```bash
curl http://localhost:8080/api/pricing/lowest-single-brand
```

---

### 7. 카테고리별 최고가/최저가 브랜드 조회

```bash
curl -G http://localhost:8080/api/pricing/min-max \
  --data-urlencode "category=스니커즈"
```

---

## 테스트 실행 방법

### 단위 테스트 & 통합 테스트 실행

```bash
./gradlew test
```

> `PricingServiceTest`, `PricingControllerIntegrationTest` 모두 실행됩니다.

### 특정 테스트만 실행

```bash
./gradlew test --tests *PricingServiceTest
```

또는

```bash
./gradlew test --tests *IntegrationTest
```

---

## H2 콘솔 접속 방법

### 접속 주소

```
http://localhost:8080/h2-console
```

### 설정 값

- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`

Spring Boot에서 H2가 메모리 모드로 실행되기 때문에, 애플리케이션이 실행 중일 때만 접속 가능합니다.

## 기타

- 초기 데이터는 `src/main/resources/data.sql`에 정의됨
