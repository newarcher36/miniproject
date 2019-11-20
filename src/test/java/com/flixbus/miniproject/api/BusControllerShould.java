package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.api.mapper.BusDtoToBusMapper;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.usecase.DeleteBus;
import com.flixbus.miniproject.usecase.EditBus;
import com.flixbus.miniproject.usecase.GetBus;
import com.flixbus.miniproject.usecase.SaveBus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BusControllerShould {

    private BusController busController;

    @Captor
    private ArgumentCaptor<Bus> captor;

    @Mock
    private SaveBus saveBus;

    @Mock
    private EditBus editBus;

    @Mock
    private GetBus getBus;

    @Mock
    private DeleteBus deleteBus;

    @Mock
    private BusDtoToBusMapper mapper;

    @BeforeEach
    void init() {
        busController = new BusController(saveBus, editBus, getBus, deleteBus, mapper);
    }

    @Test void
    save_a_bus() {

        BusDto busDto = aBusDto();
        Bus bus = aBus();

        given(mapper.map(busDto)).willReturn(bus);

        busController.createBus(busDto);

        verify(saveBus).execute(captor.capture());

        Bus actual = captor.getValue();

        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(busDto);
    }

    @Test void
    edit_a_bus() {

        BusDto busDto = aBusDto();
        Bus bus = aBus();

        given(mapper.map(busDto)).willReturn(bus);

        busController.editBus(busDto);

        verify(editBus).execute(captor.capture());

        Bus actual = captor.getValue();

        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(busDto);
    }

    @Test void
    get_a_bus_by_id() {

        given(getBus.getBusById(1L)).willReturn(aBus());

        BusDto retrievedBus = busController.getBusById(1L);

        assertThat(retrievedBus)
                .usingRecursiveComparison()
                .isEqualTo(aBus());
    }

    @Test void
    delete_a_bus_by_id() {

        busController.deleteBusById(1L);

        verify(deleteBus).deleteBusById(1L);
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