package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.BusAlreadyParkedExcepion;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkBusShould {

    private ParkBus parkBus;

    @Mock
    private DepotRepository depotRepository;

    @Mock
    private BusRepository busRepository;

    @Captor
    private ArgumentCaptor<Depot> captor;

    @BeforeEach
    void init() {
        parkBus = new ParkBus(depotRepository, busRepository);
    }

    @Test
    void park_bus_to_a_given_depot() {

        long depotId = 1L;
        long busId = 1L;
        Bus busToPark = aBus();

        given(depotRepository.findDepotById(depotId)).willReturn(Optional.of(aDepot()));
        given(busRepository.findBusById(busId)).willReturn(Optional.of(busToPark));
        given(busRepository.isBusParkedAlready(busId)).willReturn(false);

        parkBus.execute(depotId, busId);

        verify(depotRepository).save(captor.capture());

        Depot depot = captor.getValue();

        assertThat(depot.getBuses())
                .contains(busToPark);
    }
    
    @Test void
    fail_when_park_a_bus_to_a_non_existing_depot() {

        long depotId = 1L;
        long busId = 1L;

        given(depotRepository.findDepotById(1L)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> parkBus.execute(depotId, busId));

        assertThat(throwable)
                .isInstanceOf(DepotNotFoundException.class)
                .hasMessage("Depot not found with id: 1");
    }

    @Test void
    fail_when_park_a_non_existing_bus() {

        long depotId = 1L;
        long busId = 1L;

        given(depotRepository.findDepotById(1L)).willReturn(Optional.of(aDepot()));
        given(busRepository.findBusById(busId)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> parkBus.execute(depotId, busId));

        assertThat(throwable)
                .isInstanceOf(BusNotFoundException.class)
                .hasMessage("Bus not found with id: 1");
    }

    @Test void
    fail_when_a_bus_already_parked_on_another_depot() {

        long depotId = 1L;
        long busId = 1L;

        given(depotRepository.findDepotById(1L)).willReturn(Optional.of(aDepot()));
        given(busRepository.findBusById(busId)).willReturn(Optional.of(aBus()));
        given(busRepository.isBusParkedAlready(1L)).willReturn(true);

        Throwable throwable = catchThrowable(() -> parkBus.execute(depotId, busId));

        assertThat(throwable)
                .isInstanceOf(BusAlreadyParkedExcepion.class)
                .hasMessage("Bus with id 1 is already parked");
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(2)
                .withBuses(new HashSet<>(){{add(aBus());}})
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