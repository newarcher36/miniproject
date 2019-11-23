package com.flixbus.miniproject.usecase.depot;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.domain.exception.DepotNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
    void edit_a_depot() {

        Depot depot = aDepot();

        given(depotRepository.findDepotById(1L)).willReturn(Optional.of(aDepot()));

        editDepot.execute(depot);

        verify(depotRepository).save(depot);
    }

    @Test void
    fail_when_edit_a_non_existing_depot() {

        given(depotRepository.findDepotById(1L)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> editDepot.execute(aDepot()));

        assertThat(throwable)
                .isInstanceOf(DepotNotFoundException.class)
                .hasMessage("Depot not found with id: 1");
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(emptySet())
                .build();
    }
}