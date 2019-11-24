package com.flixbus.miniproject.usecase.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.exception.BusNotFoundException;
import com.flixbus.miniproject.domain.exception.DuplicatePlateNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EditBusShould {

    private static final String PLATE_NUMBER = "BUS-111-111";
    public static final String NEW_PLATE_NUMBER = "BUS-222-222";
    private EditBus editBus;

    @Mock
    private BusRepository busRepository;

    @BeforeEach
    void init() {
        editBus = new EditBus(busRepository);
    }

    @Test
    void edit_a_bus() {

        Bus currentBus = aBus();
        Bus busToEdit = aBus();

        given(busRepository.findBusById(1L)).willReturn(Optional.of(currentBus));

        editBus.execute(busToEdit);

        verify(busRepository).save(busToEdit);
    }

    @Test void
    edit_a_bus_with_a_plate_number() {

        Bus currentBus = aBus();
        Bus busToEdit = BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber(NEW_PLATE_NUMBER)
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.GREEN)
                .build();

        given(busRepository.findBusById(1L)).willReturn(Optional.of(currentBus));
        given(busRepository.existsByPlateNumber(NEW_PLATE_NUMBER)).willReturn(false);

        editBus.execute(busToEdit);

        verify(busRepository).save(busToEdit);
    }

    @Test void
    fail_when_bus_to_edit_not_exists() {

        Bus busToEdit = aBus();
        given(busRepository.findBusById(busToEdit.getId())).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> editBus.execute(busToEdit));

        assertThat(throwable)
                .isInstanceOf(BusNotFoundException.class)
                .hasMessage("Bus not found with id: 1");
    }

    @Test void
    fail_when_edit_a_bus_with_an_existing_plate_number() {

        Bus currentBus = aBus();

        Bus busToEdit = BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber(NEW_PLATE_NUMBER)
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.GREEN)
                .build();

        given(busRepository.findBusById(1L)).willReturn(Optional.of(currentBus));
        given(busRepository.existsByPlateNumber(NEW_PLATE_NUMBER)).willReturn(true);

        Throwable throwable = catchThrowable(() -> editBus.execute(busToEdit));

        assertThat(throwable)
                .isInstanceOf(DuplicatePlateNumberException.class)
                .hasMessage("Another bus has already this plate number: BUS-222-222");
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