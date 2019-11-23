package com.flixbus.miniproject.infrastructure.persistence.repository.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusToBusEntityMapper;

import javax.inject.Named;
import java.util.Optional;

@Named
public class DepotHibernateJpaRepository implements DepotRepository {

    private final DepotJpaRepository depotJpaRepository;
    private final BusEntityToBusMapper busEntityToBusMapper;
    private final BusToBusEntityMapper busToBusEntityMapper;

    public DepotHibernateJpaRepository(DepotJpaRepository depotJpaRepository, BusEntityToBusMapper busEntityToBusMapper, BusToBusEntityMapper busToBusEntityMapper) {
        this.depotJpaRepository = depotJpaRepository;
        this.busEntityToBusMapper = busEntityToBusMapper;
        this.busToBusEntityMapper = busToBusEntityMapper;
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
                .withCapacity(depot.getCapacity())
                .withBuses(busToBusEntityMapper.map(depot.getBuses()))
                .build();
    }

    private Depot mapToDepot(DepotEntity depotEntity) {
        return Depot.DepotBuilder.aDepot()
                .withId(depotEntity.getId())
                .withName(depotEntity.getName())
                .withCapacity(depotEntity.getCapacity())
                .withBuses(busEntityToBusMapper.map(depotEntity.getBuses()))
                .build();
    }
}
