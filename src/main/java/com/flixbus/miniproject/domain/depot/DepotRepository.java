package com.flixbus.miniproject.domain.depot;

import java.util.Optional;

public interface DepotRepository {

    void save(Depot depot);

    Optional<Depot> findDepotById(long depotId);

    void deleteDepotById(long depotId);
}
