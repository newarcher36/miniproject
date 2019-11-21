package com.flixbus.miniproject.infrastructure.persistence;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotHibernateJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepotHibernateJpaRepositoryShould {

    private DepotHibernateJpaRepository depotHibernateJpaRepository;

    @Mock
    private DepotJpaRepository depotJpaRepository;

    @Mock
    private BusEntityToBusMapper mapper;

    @Captor
    private ArgumentCaptor<DepotEntity> captor;

    @BeforeEach
    void init() {
        depotHibernateJpaRepository = new DepotHibernateJpaRepository(depotJpaRepository, mapper);
    }

    @Test void
    save_a_depot() {

        Depot depot = aDepot();

        depotHibernateJpaRepository.save(aDepot());

        verify(depotJpaRepository).save(captor.capture());

        DepotEntity depotEntity = captor.getValue();

        assertThat(depot)
                .isEqualToComparingFieldByField(depotEntity);

    }

    @Test void
    delete_depot_by_id() {

        depotHibernateJpaRepository.deleteDepotById(1L);

        verify(depotJpaRepository).deleteById(1L);
    }

    @Test void
    find_depot_by_id() {

        BDDMockito.given(depotJpaRepository.findById(1L)).willReturn(Optional.of(aDepotEntity()));

        Optional<Depot> optionalDepot = depotHibernateJpaRepository.findDepotById(1L);

        assertThat(optionalDepot)
                .isPresent();
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }

    private DepotEntity aDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .withBuses(Set.of(aBusEntity()))
                .build();
    }

    private BusEntity aBusEntity() {
        return BusEntity.BusEntityBuilder.aBusEntity()
                .withId(1L)
                .withPlateNumber("1908IKH")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
