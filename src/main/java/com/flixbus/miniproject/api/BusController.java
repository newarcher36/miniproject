package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.api.mapper.BusDtoToBusMapper;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.usecase.bus.DeleteBus;
import com.flixbus.miniproject.usecase.bus.EditBus;
import com.flixbus.miniproject.usecase.bus.GetBus;
import com.flixbus.miniproject.usecase.bus.SaveBus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/buses")
public class BusController {

    private final SaveBus saveBus;
    private final EditBus editBus;
    private final GetBus getBus;
    private final DeleteBus deleteBus;
    private final BusDtoToBusMapper mapper;

    public BusController(SaveBus saveBus, EditBus editBus, GetBus getBus, DeleteBus deleteBus, BusDtoToBusMapper mapper) {
        this.saveBus = saveBus;
        this.editBus = editBus;
        this.getBus = getBus;
        this.deleteBus = deleteBus;
        this.mapper = mapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void createBus(@RequestBody BusDto busDto) {
        Bus bus = mapper.map(busDto);
        saveBus.execute(bus);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void editBus(@RequestBody BusDto busDto) {
        Bus bus = mapper.map(busDto);
        editBus.execute(bus);
    }

    @GetMapping(value = "{busId}", produces = APPLICATION_JSON_VALUE)
    public BusDto getBusById(@PathVariable long busId) {
        return mapBusToBusDto(getBus.getBusById(busId));
    }

    @DeleteMapping(value = "{busId}")
    public void deleteBusById(@PathVariable long busId) {
        deleteBus.deleteBusById(busId);
    }

    private BusDto mapBusToBusDto(Bus bus) {
        return BusDto.BusDtoBuilder.aBusDto()
                .withId(bus.getId())
                .withPlateNumber(bus.getPlateNumber())
                .withBusType(bus.getBusType())
                .withBusColor(bus.getBusColor())
                .withCapacity(bus.getPassengerCapacity())
                .build();
    }
}
