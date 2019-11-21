package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.DepotNotEmptyException;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;

import javax.inject.Named;

@Named
public class DeleteDepot {

    private final DepotRepository depotRepository;

    public DeleteDepot(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    public void execute(long depotId) {

        Depot depot= findDepotById(depotId);

        int parkedBuses = depot.getBuses().size();
        if (parkedBuses > 0) {
            throw new DepotNotEmptyException(String.format("Cannot remove depot with id [%d], there are [%d] buses parked already", depotId, parkedBuses));
        }

        depotRepository.deleteDepotById(depotId);
    }

    private Depot findDepotById(long depotId) {
        return depotRepository.findDepotById(depotId)
                .orElseThrow(() -> new DepotNotFoundException("Depot not found with id: " + depotId));
    }
}
