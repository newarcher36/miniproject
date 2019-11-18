package com.flixbus.miniproject.domain.bus;

public interface BusRepository {

    Bus save(Bus bus);

    Bus findBusByPlateNumber(String plateNumber);

    Bus update(Bus bus);
}
