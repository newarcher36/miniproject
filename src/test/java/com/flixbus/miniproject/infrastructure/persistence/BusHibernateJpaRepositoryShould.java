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

import java.util.Optional;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
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
        Bus bus = aBus();
        BusEntity busEntity = aBusEntity();

        given(busJpaRepository.save(refEq(busEntity))).willReturn(busEntity);

        Bus savedBus = busHibernateJpaRepository.save(bus);

        assertThat(savedBus)
                .usingRecursiveComparison()
                .isEqualTo(bus);
    }

    @Test void
    true_when_a_given_bus_exists_by_plate_number() {
        given(busJpaRepository.existsBusEntityByPlateNumber(PLATE_NUMBER)).willReturn(true);

        boolean exists = busHibernateJpaRepository.existsByPlateNumber(PLATE_NUMBER);

        assertThat(exists).isTrue();
    }

    @Test void
    false_when_a_given_bus_exists_by_plate_number() {
        given(busJpaRepository.existsBusEntityByPlateNumber(PLATE_NUMBER)).willReturn(false);

        boolean exists = busHibernateJpaRepository.existsByPlateNumber(PLATE_NUMBER);

        assertThat(exists).isFalse();
    }

    // TODO
    @Test void
    find_a_bus_by_id() {
        BusEntity busEntity = aBusEntity();

        given(busJpaRepository.findById(1L)).willReturn(Optional.of(busEntity));

        Optional<Bus> optionalBus = busHibernateJpaRepository.findByBusId(1L);

        assertThat(optionalBus)
                .isPresent();

        assertThat(optionalBus.get())
                .usingRecursiveComparison()
                .isEqualTo(busEntity);
    }

    private BusEntity aBusEntity() {
        return BusEntityBuilder.aBusEntity()
                .withId(1L)
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
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
