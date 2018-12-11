package com.tb.bimo.repositories;

import com.tb.bimo.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
