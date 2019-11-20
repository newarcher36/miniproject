package com.flixbus.miniproject.domain.bus;

import java.util.Optional;

public interface BusRepository {

    Bus save(Bus bus);

    boolean existsByPlateNumber(String plateNumber);

    Optional<Bus> findByBusId(Long busId);
}
