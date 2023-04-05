package com.application.inditex.prices.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Persistence class for retrieving prices
 *
 * @author chema
 */

public interface PricesRepository extends JpaRepository<PricesVO, Integer> {

    @Query(value = "SELECT * FROM PRICES p " +
            "WHERE p.PRODUCT_ID = :productId " +
            "AND p.BRAND_ID = :brandId " +
            "AND p.START_DATE >= :startDate " +
            "AND p.END_DATE <= :endDate", nativeQuery = true)
    List<PricesVO> getPrice(Integer productId, Integer brandId, Date startDate, Date endDate);

}
