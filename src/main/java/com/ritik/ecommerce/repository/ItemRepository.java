package com.ritik.ecommerce.repository;

import com.ritik.ecommerce.model.GenericItem;
import com.ritik.ecommerce.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<GenericItem, Long> {
}
