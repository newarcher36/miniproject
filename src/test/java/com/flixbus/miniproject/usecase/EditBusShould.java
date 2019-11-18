package com.flixbus.miniproject.usecase;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EditBusShould {

    private static final String PLATE_NUMBER = "8711HHL";
    private EditBus editBus;

    @Mock
    private BusRepository busRepository;

    @BeforeEach
    void init() {
        editBus = new EditBus(busRepository);
    }

    @Test
    void edit_a_bus() {

        Bus busToEdit = aBus();
        Bus editedBus = aBus();

        given(busRepository.findBusByPlateNumber(PLATE_NUMBER)).willReturn(aBus());
        given(busRepository.update(busToEdit)).willReturn(editedBus);

        Bus actual = editBus.edit(busToEdit);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(busToEdit);
    }

    @Test void
    fail_when_edit_a_bus_with_an_existing_plate_number() {

        Bus busToEdit = BusBuilder.aBus()
                .withId(2L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(busRepository.findBusByPlateNumber(PLATE_NUMBER)).willReturn(aBus());

        Throwable throwable = catchThrowable(() -> editBus.edit(busToEdit));

        assertThat(throwable)
                .isInstanceOf(DuplicatePlateNumberException.class)
                .hasMessage("Another bus has already this plate number: 8711HHL");
    }

    @Test void
    a() {
        // find by pn
          // ok
          // deleted or
    }

    private Bus aBus() {
        return BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}