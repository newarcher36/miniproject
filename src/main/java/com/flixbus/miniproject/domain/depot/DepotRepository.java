package com.flixbus.miniproject.domain.depot;

public interface DepotRepository {

    void save(Depot depot);

    boolean existsById(long depotId);
}
