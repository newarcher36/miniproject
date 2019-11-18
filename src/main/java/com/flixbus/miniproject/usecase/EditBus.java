package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;

public class EditBus {

    private final BusRepository busRepository;

    public EditBus(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus edit(Bus bus) {

        final String plateNumber = bus.getPlateNumber();

        if (plateNumberAlreadyExist(plateNumber, bus.getId())) {
            throw new DuplicatePlateNumberException("Another bus has already this plate number: " + plateNumber);
        }

        return busRepository.update(bus);
    }

    private boolean plateNumberAlreadyExist(String plateNumber, Long id) {
        return !busRepository.findBusByPlateNumber(plateNumber).getId().equals(id);
    }
}
