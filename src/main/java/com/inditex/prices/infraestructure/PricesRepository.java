package com.inditex.prices.infraestructure;

import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.infraestructure.entity.PricesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PricesRepository extends JpaRepository<PricesVO, Integer> {

     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#priceDTO.productId} AND p.brandId = :#{#priceDTO.brandId} AND p.startDate >= :#{#priceDTO.startDate}")
     List<PricesVO> findByPriceDTOWithStartDate(@Param("priceDTO") PriceDTO priceDTO);

     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#priceDTO.productId} AND p.brandId = :#{#priceDTO.brandId} AND p.endDate <= :#{#priceDTO.endDate}")
     List<PricesVO> findByPriceDTOWithEndDate(@Param("priceDTO") PriceDTO priceDTO);

     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#priceDTO.productId} AND p.brandId = :#{#priceDTO.brandId} " +
             "AND p.startDate >= :#{#priceDTO.startDate} AND p.endDate <= :#{#priceDTO.endDate}")
     List<PricesVO> findByPriceDTOWithDates(@Param("priceDTO") PriceDTO priceDTO);

     @Query("SELECT p FROM PricesVO p WHERE p.productId = :#{#priceDTO.productId} AND p.brandId = :#{#priceDTO.brandId}")
     List<PricesVO> findByPriceDTOWithoutDates(@Param("priceDTO") PriceDTO priceDTO);
}

