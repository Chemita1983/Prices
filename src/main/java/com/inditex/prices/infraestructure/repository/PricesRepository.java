package com.inditex.prices.infraestructure.repository;

import com.inditex.prices.infraestructure.model.PriceDto;
import com.inditex.prices.infraestructure.repository.entity.PricesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PricesRepository extends JpaRepository<PricesVO, Integer> {
     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#priceDto.productId} AND p.brandId = :#{#priceDto.brandId} " +
             "AND p.startDate >= :#{#priceDto.startDate} AND p.endDate <= :#{#priceDto.endDate}")
     List<PricesVO> findByPriceDTOWithDates(@Param("priceDto") PriceDto priceDto);

}

