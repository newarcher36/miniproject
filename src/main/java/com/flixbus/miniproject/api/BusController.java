package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.api.mapper.BusDtoToBusMapper;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.spec.SearchCriteria;
import com.flixbus.miniproject.usecase.bus.FilterBuses;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/buses")
public class BusController {

    private final SaveBus saveBus;
    private final EditBus editBus;
    private final GetBus getBus;
    private final DeleteBus deleteBus;
    private final BusDtoToBusMapper mapper;
    private final FilterBuses filterBuses;

    public BusController(SaveBus saveBus, EditBus editBus, GetBus getBus, DeleteBus deleteBus, BusDtoToBusMapper mapper, FilterBuses filterBuses) {
        this.saveBus = saveBus;
        this.editBus = editBus;
        this.getBus = getBus;
        this.deleteBus = deleteBus;
        this.mapper = mapper;
        this.filterBuses = filterBuses;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBus(@RequestBody BusDto busDto) {
        Bus bus = mapper.map(busDto);
        saveBus.execute(bus);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void editBus(@RequestBody BusDto busDto) {
        Bus bus = mapper.map(busDto);
        editBus.execute(bus);
    }

    @GetMapping(value = "{busId}")
    public BusDto getBusById(@PathVariable long busId) {
        return mapBusToBusDto(getBus.getBusById(busId));
    }

    @GetMapping
    public List<Bus> search(@RequestParam(value = "search") String search) {

        Pattern pattern = Pattern.compile("(\\w+?)(==|<|>)(\\w+)");
        Matcher matcher = pattern.matcher(search);

        String key = "";
        String op = "";
        String val = "";

        if (matcher.find()) {
            key = matcher.group(1);
            op = matcher.group(2);
            val = matcher.group(3);
        }

        SearchCriteria criteria = new SearchCriteria(key, op, val);

        return filterBuses.execute(criteria);
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
