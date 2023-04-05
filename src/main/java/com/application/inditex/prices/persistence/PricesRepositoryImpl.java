package com.application.inditex.prices.persistence;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.PricesVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class PricesRepositoryImpl implements PricesCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PricesVO> getPriceByFilter(Price inputPrice) {

        return (List<PricesVO>) entityManager.createQuery("FROM PricesVO p " +
                        "WHERE p.productId = :productId " +
                        "AND p.brandId = :brandId " +
                        "AND p.startDate >= :startDate " +
                        "AND p.endDate <= :endDate")
                .setParameter("productId", inputPrice.getProductId())
                .setParameter("brandId", inputPrice.getBrandId())
                .setParameter("startDate", inputPrice.getStartDate())
                .setParameter("endDate", inputPrice.getEndDate())
                .getResultList();
    }
}