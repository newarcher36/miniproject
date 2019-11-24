package com.flixbus.miniproject.domain.bus;

import org.junit.jupiter.api.Test;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BusShould {

    private static final String PLATE_NUMBER = "BUS-111-111";

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
                .withPlateNumber(PLATE_NUMBER)
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus type is required");
    }

    @Test void
    require_bus_color() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber(PLATE_NUMBER)
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
                .withPlateNumber(PLATE_NUMBER)
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
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.ORANGE)
                .withCapacity(71)
                .build());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bus capacity must be between 0 and 70 people");
    }

    @Test void
    require_plate_number_match_correct_format() {

        Throwable throwable = catchThrowable(() -> aBus()
                .withPlateNumber("BUS-10-111")
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.ORANGE)
                .withCapacity(70)
                .build());

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid bus plate number BUS-10-111 must have the following format: BUS-XXX-XXX");
    }
}