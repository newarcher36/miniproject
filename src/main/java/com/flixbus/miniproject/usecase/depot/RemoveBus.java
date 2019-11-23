package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;

import javax.inject.Named;

@Named
public class RemoveBus {

    private final DepotRepository depotRepository;
    private final BusRepository busRepository;

    public RemoveBus(DepotRepository depotRepository, BusRepository busRepository) {
        this.depotRepository = depotRepository;
        this.busRepository = busRepository;
    }

    public void execute(Long depotId, Long busId) {

        Depot depot = findDepotById(depotId);
        Bus bus = findBusById(busId);

        depot.remove(bus);

        depotRepository.save(depot);
    }

    private Bus findBusById(long busId) {
        return busRepository.findBusById(busId)
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + busId));
    }

    private Depot findDepotById(long depotId) {
        return depotRepository.findDepotById(depotId)
                .orElseThrow(() -> new DepotNotFoundException("Depot not found with id: " + depotId));
    }
}
