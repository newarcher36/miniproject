package com.flixbus.miniproject.infrastructure.persistence;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.repository.bus.BusHibernateJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.bus.BusJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BusHibernateJpaRepositoryShould {

    private static final String PLATE_NUMBER = "8711HHL";
    private BusHibernateJpaRepository busHibernateJpaRepository;

    @Mock
    private BusJpaRepository busJpaRepository;

    @Mock
    private BusEntityToBusMapper mapper;

    @Captor
    private ArgumentCaptor<BusEntity> captor;

    @BeforeEach
    void init() {
        busHibernateJpaRepository = new BusHibernateJpaRepository(busJpaRepository, mapper);
    }

    @Test void
    save_a_bus() {

        BusEntity busEntity = aBusEntity();

        busHibernateJpaRepository.save(aBus());

        verify(busJpaRepository).save(captor.capture());

        assertThat(captor.getValue())
                .isEqualToComparingFieldByField(busEntity);
    }

    @Test void
    find_a_bus_by_id() {

        BusEntity busEntity = aBusEntity();

        given(busJpaRepository.findById(1L)).willReturn(Optional.of(busEntity));
        given(mapper.map(busEntity)).willReturn(aBus());

        Optional<Bus> optionalBus = busHibernateJpaRepository.findByBusId(1L);

        assertThat(optionalBus)
                .isPresent();

        assertThat(optionalBus.get())
                .isEqualToComparingFieldByField(busEntity);
    }

    @Test void
    delete_a_bus_by_id() {

        busHibernateJpaRepository.deleteBusById(1L);

        verify(busJpaRepository).deleteById(1L);
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
