package com.application.inditex.prices.persistence;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.PricesVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *  Class for retrieving {@link PricesVO} entity fromDB using hibernate criteria
 *
 * @author chema
 */

public class PricesRepositoryImpl implements PricesCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PricesVO> getPriceByFilter(Price inputPrice) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<PricesVO> criteriaPrices = criteriaBuilder.createQuery(PricesVO.class);

        final Root<PricesVO> root = criteriaPrices.from(PricesVO.class);

        criteriaPrices.select(root).where(whereConditions(criteriaBuilder, root, inputPrice));

        Query query = entityManager.createQuery(criteriaPrices);

        return query.getResultList();

    }

    private Predicate[] whereConditions(CriteriaBuilder criteriaBuilder, Root root, Price inputPrice){

        List<Predicate> conditions = new ArrayList<>();
        conditions.add(criteriaBuilder.equal(root.get("productId"), inputPrice.getProductId()));
        conditions.add(criteriaBuilder.equal(root.get("brandId"), inputPrice.getBrandId()));

        if (inputPrice.getStartDate() != null) {
            conditions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), inputPrice.getStartDate()));
        }

        if (inputPrice.getEndDate() != null) {
            conditions.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), inputPrice.getEndDate()));
        }

        return conditions.toArray(new Predicate[0]);
    }
}