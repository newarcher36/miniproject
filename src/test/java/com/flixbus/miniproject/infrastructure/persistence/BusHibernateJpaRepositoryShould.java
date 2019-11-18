package com.flixbus.miniproject.infrastructure.persistence;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.BusHibernateJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.BusJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder.aBus;
import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder.aBusEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BusHibernateJpaRepositoryShould {

    private static final String PLATE_NUMBER = "8711HHL";
    private BusHibernateJpaRepository busHibernateJpaRepository;

    @Mock
    private BusJpaRepository busJpaRepository;

    @BeforeEach
    void init() {
        busHibernateJpaRepository = new BusHibernateJpaRepository(busJpaRepository);
    }

    @Test void
    save_a_bus() {
        Bus bus = aBus()
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        BusEntity busEntity = aBusEntity()
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(busJpaRepository.save(any())).willReturn(busEntity);

        Bus savedBus = busHibernateJpaRepository.save(bus);

        assertThat(savedBus)
        .usingRecursiveComparison()
            .isEqualTo(bus);
    }

    @Test void
    update_a_bus() {
        Bus busToUpdate = aBus()
                .withId(1L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        BusEntity busEntity = aBusEntity()
                .withId(1L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(busJpaRepository.save(busEntity)).willReturn(busEntity);

        Bus updatedBus = busHibernateJpaRepository.save(busToUpdate);

        assertThat(updatedBus)
                .usingRecursiveComparison()
                .isEqualTo(busToUpdate);
    }

    @Test void
    retrieve_a_bus_given_a_plate_number() {
        BusEntity expected = aBusEntity()
                .withId(1L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();

        given(busJpaRepository.findBusEntityByPlateNumber(PLATE_NUMBER)).willReturn(expected);
        Bus actual = busHibernateJpaRepository.findBusByPlateNumber(PLATE_NUMBER);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
