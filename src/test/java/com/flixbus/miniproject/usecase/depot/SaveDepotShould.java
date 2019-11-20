package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.usecase.SaveDepot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptySet;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveDepotShould {

    private SaveDepot saveDepot;

    @Mock
    private DepotRepository depotRepository;

    @BeforeEach
    void init() {
        saveDepot = new SaveDepot(depotRepository);
    }

    @Test void
    save_a_depot() {

        Depot depotToSave = aDepot();

        saveDepot.execute(depotToSave);

        verify(depotRepository).save(depotToSave);
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .withBuses(emptySet())
                .build();
    }
}