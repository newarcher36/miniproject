package com.flixbus.miniproject.api.mapper;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.domain.bus.Bus;

import javax.inject.Named;

import static com.flixbus.miniproject.domain.bus.Bus.*;

@Named
public class BusDtoToBusMapper {

    public Bus map(BusDto busDto) {
        return BusBuilder.aBus()
                .withId(busDto.getId())
                .withPlateNumber(busDto.getPlateNumber())
                .withBusType(busDto.getBusType())
                .withBusColor(busDto.getBusColor())
                .withCapacity(busDto.getCapacity())
                .build();
    }
}
