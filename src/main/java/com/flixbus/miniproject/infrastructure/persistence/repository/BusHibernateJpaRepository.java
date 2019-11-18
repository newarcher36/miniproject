package com.flixbus.miniproject.infrastructure.persistence.repository;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;

import javax.inject.Named;

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
    public Bus findBusByPlateNumber(String plateNumber) {
        return mapToBus(busJpaRepository.findBusEntityByPlateNumber(plateNumber));
    }

    @Override
    public Bus update(Bus bus) {
        return null;
    }

    private Bus mapToBus(BusEntity savedBus) {
        return aBus()
                .withId(savedBus.getId())
                .withPlateNumber(savedBus.getPlateNumber())
                .withBusColor(savedBus.getBusColor())
                .withBusType(savedBus.getBusType())
                .withCapacity(savedBus.getCapacity())
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
