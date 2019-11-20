package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;

import javax.inject.Named;

@Named
public class SaveDepot {

    private final DepotRepository depotRepository;

    public SaveDepot(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    public void execute(Depot depot) {
        depotRepository.save(depot);
    }
}
