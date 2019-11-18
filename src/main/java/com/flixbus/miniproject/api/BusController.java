package com.flixbus.miniproject.api;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.usecase.EditBus;
import com.flixbus.miniproject.usecase.SaveBus;
import org.springframework.http.MediaType;
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

    public BusController(SaveBus saveBus, EditBus editBus) {
        this.saveBus = saveBus;
        this.editBus = editBus;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Bus createBus(@RequestBody  Bus bus) {
        return saveBus.save(bus);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Bus editBus(Bus bus) {
        return editBus.edit(bus);
    }
}
