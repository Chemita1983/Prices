package com.inditex.prices.infraestructure.database;

import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PricesRepository extends JpaRepository<PricesVO, Integer> {
     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#productQuery.productId} AND p.brandId = :#{#productQuery.brandId} " +
             "AND :#{#productQuery.startDate} BETWEEN p.startDate AND p.endDate " +
             "AND p.priority = (SELECT MAX(p2.priority) FROM PricesVO p2 WHERE p2.productId = :#{#productQuery.productId} AND :#{#productQuery.startDate} BETWEEN p2.startDate AND p2.endDate)")
     PricesVO findByPriceDTOWithDates(@Param("productQuery") ProductQuery productQuery);

}

