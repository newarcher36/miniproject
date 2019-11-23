package com.flixbus.miniproject.usecase.bus;

import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;

import javax.inject.Named;

@Named
public class DeleteBus {
    private final BusRepository busRepository;

    public DeleteBus(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void deleteBusById(long busId) {
        busRepository.findBusById(1L)
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + busId));
        busRepository.deleteBusById(busId);
    }
}
