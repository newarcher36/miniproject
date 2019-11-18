package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;

import javax.inject.Named;

@Named
public class SaveBus {

    private final BusRepository busRepository;

    public SaveBus(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus save(Bus bus) {

        final String plateNumber = bus.getPlateNumber();

        if (plateNumberAlreadyExist(plateNumber)) {
            throw new DuplicatePlateNumberException("Another bus has already this plate number: " + plateNumber);
        }

        return busRepository.save(bus);
    }

    private boolean plateNumberAlreadyExist(String plateNumber) {
        return busRepository.findBusByPlateNumber(plateNumber) != null;
    }
}
