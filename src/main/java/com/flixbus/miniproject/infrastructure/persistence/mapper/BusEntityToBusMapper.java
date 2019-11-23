package com.flixbus.miniproject.infrastructure.persistence.mapper;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;

import javax.inject.Named;
import java.util.Set;
import java.util.stream.Collectors;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;

@Named
public class BusEntityToBusMapper {

    public Set<Bus> map(Set<BusEntity> busEntities) {
        return busEntities.stream().map(this::map).collect(Collectors.toSet());
    }

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
