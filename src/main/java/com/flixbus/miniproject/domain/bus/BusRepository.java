package com.flixbus.miniproject.domain.bus;

import java.util.Optional;
import java.util.Set;

public interface BusRepository {

    void save(Bus bus);

    boolean existsByPlateNumber(String plateNumber);

    Optional<Bus> findBusById(Long busId);

    void deleteBusById(long busId);

    Set<Bus> findAllById(Set<Long> busIds);

    boolean isBusParkedAlready(long busId);
}
