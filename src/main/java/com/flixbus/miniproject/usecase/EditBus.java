package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;

import javax.inject.Named;

@Named
public class EditBus {

    private final BusRepository busRepository;

    public EditBus(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus edit(Bus busToEdit) {

        long busId = busToEdit.getId();
        Bus currentBus = busRepository.findByBusId(busToEdit.getId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + busId));

        if (hasPlateNumberChanged(busToEdit, currentBus)) {
            checkPlateNumberDuplicated(busToEdit);
        }

        return busRepository.save(busToEdit);
    }

    private boolean hasPlateNumberChanged(Bus busToEdit, Bus currentBus) {
        return !busToEdit.getPlateNumber().equals(currentBus.getPlateNumber());
    }

    private void checkPlateNumberDuplicated(Bus busToEdit) {
        final String plateNumber = busToEdit.getPlateNumber();

        if (isPlateNumberDuplicated(plateNumber)) {
            throw new DuplicatePlateNumberException("Another bus has already this plate number: " + plateNumber);
        }
    }

    private boolean isPlateNumberDuplicated(String plateNumber) {
        return busRepository.existsByPlateNumber(plateNumber);
    }
}
