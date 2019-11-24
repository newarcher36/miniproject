package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.DepotBusyException;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteDepotShould {

    private DeleteDepot deleteDepot;

    @Mock
    private DepotRepository depotRepository;

    @BeforeEach
    void init() {
        deleteDepot = new DeleteDepot(depotRepository);
    }

    @Test void
    delete_a_depot_by_id() {

        given(depotRepository.findDepotById(1L)).willReturn(Optional.of(anEmptyDepot()));

        deleteDepot.execute(1L);

        verify(depotRepository).deleteDepotById(1L);
    }

    @Test void
    fail_when_delete_a_non_existing_depot() {

        given(depotRepository.findDepotById(1L)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> deleteDepot.execute(1L));

        assertThat(throwable)
                .isInstanceOf(DepotNotFoundException.class)
                .hasMessage("Depot not found with id: 1");
    }

    @Test void
    fail_when_delete_a_depot_with_buses() {

        given(depotRepository.findDepotById(1L)).willReturn(Optional.of(aDepot()));

        Throwable throwable = catchThrowable(() -> deleteDepot.execute(1L));

        assertThat(throwable)
                .isInstanceOf(DepotBusyException.class)
                .hasMessage("Cannot remove depot with id 1, there are 1 buses parked already");
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(Collections.singleton(aBus()))
                .build();
    }

    private Depot anEmptyDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(Collections.emptySet())
                .build();
    }

    private Bus aBus() {
        return Bus.BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("BUS-111-111")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}