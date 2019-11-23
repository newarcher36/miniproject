package com.flixbus.miniproject.usecase.bus;

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

    public void execute(Bus bus) {

        final String plateNumber = bus.getPlateNumber();

        if (plateNumberAlreadyExist(plateNumber)) {
            throw new DuplicatePlateNumberException("A bus has already the plate number " + plateNumber);
        }

        busRepository.save(bus);
    }

    private boolean plateNumberAlreadyExist(String plateNumber) {
        return busRepository.existsByPlateNumber(plateNumber);
    }
}
