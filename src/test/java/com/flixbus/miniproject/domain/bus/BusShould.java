package com.flixbus.miniproject.domain.bus;

import org.junit.jupiter.api.Test;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BusShould {

    @Test void
    require_plate_number() {

        Throwable throwable = catchThrowable(() -> aBus().build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Plate number is required");
    }

    @Test void
    require_bus_type() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber("plate_number")
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus type is required");
    }

    @Test void
    require_bus_color() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber("plate_number")
                .withBusType(BusType.DOUBLE_DECKER)
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus color is required");
    }

    @Test void
    require_capacity_equal_or_over_than_zero() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber("plate_number")
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.ORANGE)
                .withCapacity(-10)
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus capacity must be between 0 and 70 people");
    }

    @Test void
    require_capacity_equal_or_less_than_seventy() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber("plate_number")
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.ORANGE)
                .withCapacity(71)
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus capacity must be between 0 and 70 people");
    }
}