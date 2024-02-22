package com.inditex.prices.infraestructure.database;

import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PricesRepository extends JpaRepository<PricesVO, Integer> {
     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#productQuery.productId} AND p.brandId = :#{#productQuery.brandId} " +
             "AND :#{#productQuery.startDate} BETWEEN p.startDate AND p.endDate ORDER BY p.startDate ASC")
     List<PricesVO> findByPriceDTOWithDates(@Param("productQuery") ProductQuery productQuery);

}

