package com.flixbus.miniproject.infrastructure.persistence.repository;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;

import javax.inject.Named;
import java.util.Optional;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;
import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder.aBusEntity;

@Named
public class BusHibernateJpaRepository implements BusRepository {

    private final BusJpaRepository busJpaRepository;

    public BusHibernateJpaRepository(BusJpaRepository busJpaRepository) {
        this.busJpaRepository = busJpaRepository;
    }

    @Override
    public Bus save(Bus bus) {
        BusEntity busEntity = mapToBusEntity(bus);
        return mapToBus(busJpaRepository.save(busEntity));
    }

    @Override
    public boolean existsByPlateNumber(String plateNumber) {
        return busJpaRepository.existsBusEntityByPlateNumber(plateNumber);

    }

    @Override
    public Optional<Bus> findByBusId(Long busId) {
        Optional<BusEntity> optional = busJpaRepository.findById(busId);
        return optional.map(this::mapToBus);
    }

    private Bus mapToBus(BusEntity busEntity) {
        return aBus()
                .withId(busEntity.getId())
                .withPlateNumber(busEntity.getPlateNumber())
                .withBusColor(busEntity.getBusColor())
                .withBusType(busEntity.getBusType())
                .withCapacity(busEntity.getCapacity())
                .build();
    }

    private BusEntity mapToBusEntity(Bus bus) {
        return aBusEntity()
                .withId(bus.getId())
                .withPlateNumber(bus.getPlateNumber())
                .withBusType(bus.getBusType())
                .withBusColor(bus.getBusColor())
                .withCapacity(bus.getCapacity())
                .build();
    }
}
