package com.flixbus.miniproject.domain.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.exception.DepotBusyException;
import org.junit.jupiter.api.Test;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static com.flixbus.miniproject.domain.depot.Depot.DepotBuilder;
import static org.apache.commons.collections4.SetUtils.hashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DepotShould {

    @Test void
    require_depot_name() {

        Throwable throwable = catchThrowable(() -> DepotBuilder.aDepot().build());

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Depot name is required");
    }

    @Test void
    require_depot_capacity() {

        Throwable throwable = catchThrowable(() -> DepotBuilder.aDepot()
                .withName("Bavaria").build());

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Depot capacity is required");
    }

    @Test void
    when_no_buses_parked_then_depot_is_empty() {

        Depot emptyDepot = aDepot();

        assertThat(emptyDepot.getBuses())
                .isEmpty();
    }

    @Test void
    add_a_bus_to_depot() {

        Depot depot = aDepot();
        depot.add(aBus());

        assertThat(depot.getBuses()).contains(aBus());
    }

    @Test void
    fail_when_add_a_bus_to_a_full_depot() {

        Depot depot = aFullDepot();
        Throwable throwable = catchThrowable(() -> depot.add(aBus()));

        assertThat(throwable)
                .isInstanceOf(DepotBusyException.class)
                .hasMessage("Cannot park more buses. Depot with id 1 is busy");
    }

    @Test void
    remove_a_bus_from_depot() {

        Depot depot = aFullDepot();
        depot.remove(aBus());

        assertThat(depot.getBuses()).doesNotContain(aBus());
    }

    private Depot aDepot() {
        return DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(1)
                .build();
    }

    private Depot aFullDepot() {
        return DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(1)
                .withBuses(hashSet(aBus()))
                .build();
    }

    private Bus aBus() {
        return BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("BUS-111-111")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}