package com.flixbus.miniproject.infrastructure.persistence.repository.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;

import javax.inject.Named;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Optional<Depot> findDepotById(long depotId) {
        Optional<DepotEntity> optional = depotJpaRepository.findById(depotId);
        return optional.map(this::mapToDepot);
    }

    @Override
    public void deleteDepotById(long depotId) {
        depotJpaRepository.deleteById(1L);
    }

    private DepotEntity mapToDepotEntity(Depot depot) {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(depot.getId())
                .withName(depot.getName())
                .withBusCapacity(depot.getBusCapacity())
                .build();
    }

    private Depot mapToDepot(DepotEntity depotEntity) {
        return Depot.DepotBuilder.aDepot()
                .withId(depotEntity.getId())
                .withName(depotEntity.getName())
                .withBusCapacity(depotEntity.getBusCapacity())
                .withBuses(mapToBuses(depotEntity.getBuses()))
                .build();

    }

    private Set<Bus> mapToBuses(Set<BusEntity> buses) {
        return buses.stream().map(mapper::map).collect(Collectors.toSet());
    }
}
