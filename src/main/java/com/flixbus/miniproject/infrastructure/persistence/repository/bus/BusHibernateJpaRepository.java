package com.flixbus.miniproject.infrastructure.persistence.repository.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusToBusEntityMapper;

import javax.inject.Named;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Named
public class BusHibernateJpaRepository implements BusRepository {

    private final BusJpaRepository busJpaRepository;
    private final BusEntityToBusMapper busEntityToBusMapper;
    private final BusToBusEntityMapper busToBusEntityMapper;

    public BusHibernateJpaRepository(BusJpaRepository busJpaRepository, BusEntityToBusMapper busEntityToBusMapper, BusToBusEntityMapper busToBusEntityMapper) {
        this.busJpaRepository = busJpaRepository;
        this.busEntityToBusMapper = busEntityToBusMapper;
        this.busToBusEntityMapper = busToBusEntityMapper;
    }

    @Override
    public void save(Bus bus) {
        BusEntity busEntity = busToBusEntityMapper.map(bus);
        busJpaRepository.save(busEntity);
    }

    @Override
    public boolean existsByPlateNumber(String plateNumber) {
        return busJpaRepository.existsBusEntityByPlateNumber(plateNumber);
    }

    @Override
    public Optional<Bus> findBusById(Long busId) {
        Optional<BusEntity> optional = busJpaRepository.findById(busId);
        return optional.map(busEntityToBusMapper::map);
    }

    @Override
    public void deleteBusById(long busId) {
        busJpaRepository.deleteById(busId);
    }

    @Override
    public Set<Bus> findAllById(Set<Long> busIds) {
        Set<Bus> buses = new HashSet<>();
        busJpaRepository.findAllById(busIds)
                .forEach(busEntity -> buses.add(mapTo(busEntity)));

        return buses;
    }

    @Override
    public boolean isBusParkedAlready(long busId) {
        return busJpaRepository.isBusParkedAlready(busId);
    }

    private Bus mapTo(BusEntity busEntity) {
        return busEntityToBusMapper.map(busEntity);
    }
}
