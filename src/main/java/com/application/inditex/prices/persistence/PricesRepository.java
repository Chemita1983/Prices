package com.application.inditex.prices.persistence;

import com.application.inditex.prices.entity.PricesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Jpa interface repository for manage dao operations
 *
 * @author chema
 */

@Repository
public interface PricesRepository extends JpaRepository<PricesVO, Integer>, PricesCustomRepository {
    //Aquí se podría declarar el método getPricesBySearch con @Query y poner la query nativa, esto nos ahorraría 2 clases.
}
