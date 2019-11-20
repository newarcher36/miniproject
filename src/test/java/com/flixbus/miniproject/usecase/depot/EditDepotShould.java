package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;
import com.flixbus.miniproject.usecase.EditDepot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class EditDepotShould {

    private EditDepot editDepot;

    @Mock
    private DepotRepository depotRepository;

    @BeforeEach
    void init() {
        editDepot = new EditDepot(depotRepository);
    }

    @Test
    void edit_a_bus() {

        given(depotRepository.existsById(1L)).willReturn(true);

        editDepot.execute(aDepot());

        verify(depotRepository).save(refEq(aDepot()));
    }

    @Test void
    fail_when_edit_an_unexisting_depot() {

        given(depotRepository.existsById(1L)).willReturn(false);

        Throwable throwable = catchThrowable(() -> editDepot.execute(aDepot()));

        assertThat(throwable)
                .isInstanceOf(DepotNotFoundException.class)
                .hasMessage("Depot not found with id: 1");
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .withBuses(emptySet())
                .build();
    }
}