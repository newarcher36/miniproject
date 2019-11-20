package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.flixbus.miniproject.domain.bus.Bus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetBusShould {

    private GetBus getBus;

    @Mock
    private BusRepository busRepository;


    @BeforeEach
    void init() {
        getBus = new GetBus(busRepository);
    }

    @Test void
    get_a_bus_by_id() {
        Bus expected = BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(busRepository.findByBusId(1L)).willReturn(Optional.of(expected));

        Bus retrievedBus = getBus.getBusById(1L);

        assertThat(retrievedBus)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test void
    fail_when_bus_not_found_by_given_id() {

        given(busRepository.findByBusId(1L)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> getBus.getBusById(1L));

        assertThat(throwable)
                .isInstanceOf(BusNotFoundException.class)
                .hasMessage("Bus not found with id: 1");
    }
}