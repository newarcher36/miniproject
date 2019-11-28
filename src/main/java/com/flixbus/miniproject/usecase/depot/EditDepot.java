package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;

import javax.inject.Named;

@Named
public class EditDepot {

    private final DepotRepository depotRepository;

    public EditDepot(DepotRepository depotRepository) {

        this.depotRepository = depotRepository;
    }

    public void execute(Depot depot) {

        long depotId = depot.getId();

        if(notExists(depotId)) {
            throw new DepotNotFoundException("Depot not found with id: " + depotId);
        }

        depotRepository.save(depot);
    }

    private boolean notExists(long depotId) {
        return !depotRepository.findDepotById(depotId).isPresent();
    }
}
