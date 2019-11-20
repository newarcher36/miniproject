package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;

import javax.inject.Named;

@Named
public class GetBus {
    private final BusRepository busRepository;

    public GetBus(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus getBusById(long busId) {
        return busRepository.findByBusId(busId)
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + busId));
    }
}
