package com.flixbus.miniproject.infrastructure.persistence.repository.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;

import javax.inject.Named;
import java.util.Optional;

import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder.aBusEntity;

@Named
public class BusHibernateJpaRepository implements BusRepository {

    private final BusJpaRepository busJpaRepository;
    private final BusEntityToBusMapper mapper;

    public BusHibernateJpaRepository(BusJpaRepository busJpaRepository, BusEntityToBusMapper mapper) {
        this.busJpaRepository = busJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Bus bus) {
        BusEntity busEntity = map(bus);
        busJpaRepository.save(busEntity);
    }

    @Override
    public boolean existsByPlateNumber(String plateNumber) {
        return busJpaRepository.existsBusEntityByPlateNumber(plateNumber);

    }

    @Override
    public Optional<Bus> findByBusId(Long busId) {
        Optional<BusEntity> optional = busJpaRepository.findById(busId);
        return optional.map(mapper::map);
    }

    @Override
    public void deleteBusById(long busId) {
        busJpaRepository.deleteById(busId);
    }

    private BusEntity map(Bus bus) {
        return aBusEntity()
                .withId(bus.getId())
                .withPlateNumber(bus.getPlateNumber())
                .withBusType(bus.getBusType())
                .withBusColor(bus.getBusColor())
                .withCapacity(bus.getPassengerCapacity())
                .build();
    }
}
