package com.flixbus.miniproject.usecase.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeleteBusShould {

    private DeleteBus deleteBus;

    @Mock
    private BusRepository busRepository;

    @BeforeEach
    void init() {
        deleteBus = new DeleteBus(busRepository);
    }

    @Test
    void
    delete_a_bus_by_id() {

        Bus currentBus = Bus.BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        BDDMockito.given(busRepository.findBusById(1L)).willReturn(Optional.of(currentBus));

        deleteBus.deleteBusById(1L);

        BDDMockito.verify(busRepository).deleteBusById(1L);
    }

    @Test void
    fail_when_delete_a_non_existing_bus_by_id() {

        given(busRepository.findBusById(1L)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> deleteBus.deleteBusById(1L));

        assertThat(throwable)
                .isInstanceOf(BusNotFoundException.class)
                .hasMessage("Bus not found with id: 1");
    }
}