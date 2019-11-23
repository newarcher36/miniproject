package com.flixbus.miniproject.infrastructure.persistence.repository.bus;

import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BusJpaRepository extends CrudRepository<BusEntity, Long>, JpaSpecificationExecutor<BusEntity> {

    boolean existsBusEntityByPlateNumber(String plateNumber);

    @Query(value = "select (count(b) > 0) from depot d join d.buses b where d.id = b.id and b.id = ?1")
    boolean isBusParkedAlready(long id);
}
