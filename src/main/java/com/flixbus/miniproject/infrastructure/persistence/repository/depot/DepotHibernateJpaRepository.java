package com.flixbus.miniproject.infrastructure.persistence.repository.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;

import javax.inject.Named;

@Named
public class DepotHibernateJpaRepository implements DepotRepository {

    private final DepotJpaRepository depotJpaRepository;
    private final BusEntityToBusMapper mapper;

    public DepotHibernateJpaRepository(DepotJpaRepository depotJpaRepository, BusEntityToBusMapper mapper) {
        this.depotJpaRepository = depotJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Depot depot) {
        depotJpaRepository.save(mapToDepotEntity(depot));
    }

    @Override
    public boolean existsById(long depotId) {
        return false;
    }

    private DepotEntity mapToDepotEntity(Depot depot) {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(depot.getId())
                .withName(depot.getName())
                .withBusCapacity(depot.getBusCapacity())
                .build();
    }
//
//    private Set<BusEntity> mapToBusEntity(Set<Bus> buses) {
//        return buses.stream().map(mapper::map).collect(Collectors.toSet());
//    }
}
