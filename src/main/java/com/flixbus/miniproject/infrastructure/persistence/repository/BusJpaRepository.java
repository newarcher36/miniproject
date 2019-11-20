package com.flixbus.miniproject.infrastructure.persistence.repository;

import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import org.springframework.data.repository.CrudRepository;

public interface BusJpaRepository extends CrudRepository<BusEntity, Long> {

    boolean existsBusEntityByPlateNumber(String plateNumber);
}
