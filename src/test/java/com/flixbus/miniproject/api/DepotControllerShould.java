package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.usecase.depot.EditDepot;
import com.flixbus.miniproject.usecase.depot.SaveDepot;
import com.flixbus.miniproject.usecase.depot.DeleteDepot;
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

    @Captor
    private ArgumentCaptor<Depot> captor;

    @BeforeEach
    void init() {
        depotController = new DepotController(saveDepot, editDepot, deleteDepot);
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
                .withBusCapacity(12)
                .build();
    }
}
