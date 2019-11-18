package com.flixbus.miniproject.api;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.usecase.EditBus;
import com.flixbus.miniproject.usecase.SaveBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BusControllerShould {

    private BusController busController;

    @Mock
    private SaveBus saveBus;

    @Mock
    private EditBus editBus;

    @BeforeEach
    void init() {
        busController = new BusController(saveBus, editBus);
    }

    @Test void
    save_a_bus() {

        Bus busToSave = BusBuilder.aBus()
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(saveBus.save(busToSave)).willReturn(busToSave);

        Bus savedBus = busController.createBus(busToSave);

        assertThat(savedBus)
                .isEqualTo(busToSave);
    }

    @Test void
    edit_a_bus() {

        Bus busToEdit = BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(editBus.edit(busToEdit)).willReturn(busToEdit);

        Bus savedBus = busController.editBus(busToEdit);

        assertThat(savedBus)
                .isEqualTo(busToEdit);
    }
}