package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.api.mapper.BusDtoToBusMapper;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.usecase.bus.DeleteBus;
import com.flixbus.miniproject.usecase.bus.EditBus;
import com.flixbus.miniproject.usecase.bus.FilterBuses;
import com.flixbus.miniproject.usecase.bus.GetBus;
import com.flixbus.miniproject.usecase.bus.SaveBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BusControllerShould {

    private BusController busController;

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

    @Mock
    private FilterBuses filterBuses;

    @BeforeEach
    void init() {
        busController = new BusController(saveBus, editBus, getBus, deleteBus, mapper, filterBuses);
    }

    @Test void
    save_a_bus() {

        BusDto busDto = aBusDto();
        Bus bus = aBus();

        given(mapper.map(busDto)).willReturn(bus);

        busController.createBus(busDto);

        verify(saveBus).execute(bus);
    }

    @Test void
    edit_a_bus() {

        BusDto busDto = aBusDto();
        Bus bus = aBus();

        given(mapper.map(busDto)).willReturn(bus);

        busController.editBus(busDto);

        verify(editBus).execute(bus);
    }

    @Test void
    get_a_bus_by_id() {

        given(getBus.getBusById(1L)).willReturn(aBus());

        BusDto retrievedBus = busController.getBusById(1L);

        assertThat(retrievedBus)
                .isEqualToComparingFieldByField(aBus());
    }

    @Test void
    delete_a_bus_by_id() {

        busController.deleteBusById(1L);

        verify(deleteBus).deleteBusById(1L);
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

    private BusDto aBusDto() {
        return BusDto.BusDtoBuilder.aBusDto()
                .withId(1L)
                .withPlateNumber("BUS-111-111")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}