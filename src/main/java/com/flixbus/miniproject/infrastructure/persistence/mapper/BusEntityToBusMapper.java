package com.flixbus.miniproject.infrastructure.persistence.mapper;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;

import javax.inject.Named;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;

@Named
public class BusEntityToBusMapper {

    public Bus map(BusEntity busEntity) {
        return aBus()
                .withId(busEntity.getId())
                .withPlateNumber(busEntity.getPlateNumber())
                .withBusColor(busEntity.getBusColor())
                .withBusType(busEntity.getBusType())
                .withCapacity(busEntity.getPassengerCapacity())
                .build();
    }
}
