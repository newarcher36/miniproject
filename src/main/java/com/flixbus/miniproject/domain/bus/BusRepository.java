package com.flixbus.miniproject.domain.bus;

import com.flixbus.miniproject.domain.bus.spec.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface BusRepository {

    void save(Bus bus);

    boolean existsByPlateNumber(String plateNumber);

    Optional<Bus> findBusById(Long busId);

    void deleteBusById(long busId);

    List<Bus> findAllById(SearchCriteria criteria);

    boolean isBusParkedAlready(long busId);
}
