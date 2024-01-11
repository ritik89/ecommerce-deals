package com.ritik.ecommerce.repository;

import com.ritik.ecommerce.model.Deal;
import com.ritik.ecommerce.model.LimitedDeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends CrudRepository<LimitedDeal, Long> {
}
