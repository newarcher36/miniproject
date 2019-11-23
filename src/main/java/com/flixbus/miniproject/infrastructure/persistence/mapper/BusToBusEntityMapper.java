package com.flixbus.miniproject.infrastructure.persistence.mapper;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;

import javax.inject.Named;
import java.util.Set;
import java.util.stream.Collectors;

import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder.aBusEntity;

@Named
public class BusToBusEntityMapper {

    public Set<BusEntity> map(Set<Bus> buses) {
        return buses.stream().map(this::map).collect(Collectors.toSet());
    }

    public BusEntity map(Bus bus) {
        return aBusEntity()
                .withId(bus.getId())
                .withPlateNumber(bus.getPlateNumber())
                .withBusColor(bus.getBusColor())
                .withBusType(bus.getBusType())
                .withCapacity(bus.getPassengerCapacity())
                .build();
    }
}
