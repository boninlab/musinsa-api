-- 브랜드 등록
INSERT INTO brands (id, name) VALUES
                                  (1, 'A'), (2, 'B'), (3, 'C'), (4, 'D'), (5, 'E'),
                                  (6, 'F'), (7, 'G'), (8, 'H'), (9, 'I');

-- 브랜드별 상품 가격 삽입
INSERT INTO brand_products (brand_id, category, price) VALUES
-- A
(1, 'TOP', 11200), (1, 'OUTER', 5500), (1, 'PANTS', 4200), (1, 'SNEAKERS', 9000),
(1, 'BAG', 2000), (1, 'HAT', 1700), (1, 'SOCKS', 1800), (1, 'ACCESSORY', 2300),

-- B
(2, 'TOP', 10500), (2, 'OUTER', 5900), (2, 'PANTS', 3800), (2, 'SNEAKERS', 9100),
(2, 'BAG', 2100), (2, 'HAT', 2000), (2, 'SOCKS', 2000), (2, 'ACCESSORY', 2200),

-- C
(3, 'TOP', 10000), (3, 'OUTER', 6200), (3, 'PANTS', 3300), (3, 'SNEAKERS', 9200),
(3, 'BAG', 2200), (3, 'HAT', 1900), (3, 'SOCKS', 2200), (3, 'ACCESSORY', 2100),

-- D
(4, 'TOP', 10100), (4, 'OUTER', 5100), (4, 'PANTS', 3000), (4, 'SNEAKERS', 9500),
(4, 'BAG', 2500), (4, 'HAT', 1500), (4, 'SOCKS', 2400), (4, 'ACCESSORY', 2000),

-- E
(5, 'TOP', 10700), (5, 'OUTER', 5000), (5, 'PANTS', 3800), (5, 'SNEAKERS', 9900),
(5, 'BAG', 2300), (5, 'HAT', 1800), (5, 'SOCKS', 2100), (5, 'ACCESSORY', 2100),

-- F
(6, 'TOP', 11200), (6, 'OUTER', 7200), (6, 'PANTS', 4000), (6, 'SNEAKERS', 9300),
(6, 'BAG', 2100), (6, 'HAT', 1600), (6, 'SOCKS', 2300), (6, 'ACCESSORY', 1900),

-- G
(7, 'TOP', 10500), (7, 'OUTER', 5800), (7, 'PANTS', 3900), (7, 'SNEAKERS', 9000),
(7, 'BAG', 2200), (7, 'HAT', 1700), (7, 'SOCKS', 2100), (7, 'ACCESSORY', 2000),

-- H
(8, 'TOP', 10800), (8, 'OUTER', 6300), (8, 'PANTS', 3100), (8, 'SNEAKERS', 9700),
(8, 'BAG', 2100), (8, 'HAT', 1600), (8, 'SOCKS', 2000), (8, 'ACCESSORY', 2000),

-- I
(9, 'TOP', 11400), (9, 'OUTER', 6700), (9, 'PANTS', 3200), (9, 'SNEAKERS', 9500),
(9, 'BAG', 2400), (9, 'HAT', 1700), (9, 'SOCKS', 1700), (9, 'ACCESSORY', 2400);
