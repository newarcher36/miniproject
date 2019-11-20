package com.flixbus.miniproject.infrastructure.persistence.repository.depot;

import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepotJpaRepository extends CrudRepository<DepotEntity, Long> {
}
