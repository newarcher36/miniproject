package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.usecase.EditBus;
import com.flixbus.miniproject.usecase.GetBus;
import com.flixbus.miniproject.usecase.SaveBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BusControllerShould {

    private BusController busController;

    @Mock
    private SaveBus saveBus;

    @Mock
    private EditBus editBus;

    @Mock
    private GetBus getBus;

    @BeforeEach
    void init() {
        busController = new BusController(saveBus, editBus, getBus);
    }

    @Test void
    save_a_bus() {

        BusDto busDto = aBusDto();
        Bus busToSave = aBus();

        given(saveBus.save(refEq(busToSave))).willReturn(busToSave);

        BusDto savedBus = busController.createBus(busDto);

        assertThat(savedBus)
                .usingRecursiveComparison()
                .isEqualTo(busDto);
    }

    @Test void
    edit_a_bus() {

        BusDto busDto = aBusDto();
        Bus busToEdit = aBus();

        given(editBus.edit(refEq(busToEdit))).willReturn(busToEdit);

        BusDto savedBus = busController.editBus(busDto);

        assertThat(savedBus)
                .usingRecursiveComparison()
                .isEqualTo(busToEdit);
    }

    @Test void
    get_a_bus_by_id() {

        given(getBus.getBusById(1L)).willReturn(aBus());

        BusDto retrievedBus = busController.getBusById(1L);

        assertThat(retrievedBus)
                .usingRecursiveComparison()
                .isEqualTo(aBus());
    }

    private Bus aBus() {
        return BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }

    private BusDto aBusDto() {
        return BusDto.BusDtoBuilder.aBusDto()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}