package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.usecase.EditBus;
import com.flixbus.miniproject.usecase.GetBus;
import com.flixbus.miniproject.usecase.SaveBus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.flixbus.miniproject.domain.bus.Bus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/buses")
public class BusController {

    private final SaveBus saveBus;
    private final EditBus editBus;
    private final GetBus getBus;

    public BusController(SaveBus saveBus, EditBus editBus, GetBus getBus) {
        this.saveBus = saveBus;
        this.editBus = editBus;
        this.getBus = getBus;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BusDto createBus(@RequestBody BusDto busDto) {
        Bus bus = mapBusDtoToBus(busDto);
        return mapBusToBusDto(saveBus.save(bus));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BusDto editBus(@RequestBody BusDto busDto) {
        Bus bus = mapBusDtoToBus(busDto);
        return mapBusToBusDto(editBus.edit(bus));
    }

    @GetMapping(value = "{busId}", produces = APPLICATION_JSON_VALUE)
    public BusDto getBusById(@PathVariable long busId) {
        return mapBusToBusDto(getBus.getBusById(busId));
    }

    private Bus mapBusDtoToBus(BusDto busDto) {
        return BusBuilder.aBus()
                .withId(busDto.getId())
                .withPlateNumber(busDto.getPlateNumber())
                .withBusType(busDto.getBusType())
                .withBusColor(busDto.getBusColor())
                .withCapacity(busDto.getCapacity())
                .build();
    }

    private BusDto mapBusToBusDto(Bus bus) {
        return BusDto.BusDtoBuilder.aBusDto()
                .withId(bus.getId())
                .withPlateNumber(bus.getPlateNumber())
                .withBusType(bus.getBusType())
                .withBusColor(bus.getBusColor())
                .withCapacity(bus.getCapacity())
                .build();
    }
}
