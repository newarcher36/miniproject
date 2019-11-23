package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.usecase.depot.RemoveBus;
import com.flixbus.miniproject.usecase.depot.DeleteDepot;
import com.flixbus.miniproject.usecase.depot.EditDepot;
import com.flixbus.miniproject.usecase.depot.ParkBus;
import com.flixbus.miniproject.usecase.depot.SaveDepot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class DepotControllerShould {

    private DepotController depotController;

    @Mock
    private SaveDepot saveDepot;

    @Mock
    private EditDepot editDepot;

    @Mock
    private DeleteDepot deleteDepot;

    @Mock
    private ParkBus parkBus;

    @Mock
    private RemoveBus removeBus;

    @Captor
    private ArgumentCaptor<Depot> captor;

    @BeforeEach
    void init() {
        depotController = new DepotController(saveDepot, editDepot, deleteDepot, parkBus, removeBus);
    }

    @Test void
    save_a_depot() {

        DepotWriteDto depotWriteDto = aDepotWritetDto();

        depotController.createDepot(depotWriteDto);

        verify(saveDepot).execute(captor.capture());

        Depot depot = captor.getValue();

        assertThat(depotWriteDto)
                .isEqualToComparingFieldByField(depot);
    }
    
    @Test void
    edit_a_depot() {

        DepotWriteDto depotWriteDto = aDepotWritetDto();

        depotController.editDepot(depotWriteDto);

        verify(editDepot).execute(captor.capture());

        Depot depot = captor.getValue();

        assertThat(depotWriteDto)
                .isEqualToComparingFieldByField(depot);
    }

    @Test void
    delete_a_depot_by_id() {

        depotController.deleteDepotById(1L);

        verify(deleteDepot).execute(1L);
    }

    @Test void
    park_buses_to_a_given_depot() {

        long depotId = 1L;
        long busId = 1L;

        depotController.parkBusesToDepot(depotId, busId);

        verify(parkBus).execute(depotId, busId);
    }

    @Test void
    remove_bus_from_depot() {

        long depotId = 1L;
        long busId = 1L;

        depotController.removeBusesToDepot(depotId, busId);

        verify(removeBus).execute(depotId, busId);
    }

    private DepotWriteDto aDepotWritetDto() {
        return DepotWriteDto.DepotWriteDtoBuilder.aDepotWriteDto()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .build();
    }
}
