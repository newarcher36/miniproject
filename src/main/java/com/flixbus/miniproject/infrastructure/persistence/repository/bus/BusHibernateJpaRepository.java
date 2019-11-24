package com.flixbus.miniproject.infrastructure.persistence.repository.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.spec.BusSpecification;
import com.flixbus.miniproject.domain.bus.spec.SearchCriteria;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusToBusEntityMapper;
import org.springframework.data.jpa.domain.Specification;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Bus> findAllById(SearchCriteria searchCriteria) {
        Specification<BusEntity> specification = new BusSpecification(searchCriteria);
        return busJpaRepository.findAll(specification).stream()
                .map(busEntityToBusMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBusParkedAlready(long busId) {
        return busJpaRepository.isBusParkedAlready(busId);
    }
}
