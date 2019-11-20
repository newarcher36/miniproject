package com.flixbus.miniproject.infrastructure.persistence;

import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.mapper.BusEntityToBusMapper;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotHibernateJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepotHibernateJpaRepositoryShould {

    private DepotHibernateJpaRepository depotHibernateJpaRepository;

    @Mock
    private DepotJpaRepository depotJpaRepository;

    @Mock
    private BusEntityToBusMapper mapper;

    @BeforeEach
    void init() {
        depotHibernateJpaRepository = new DepotHibernateJpaRepository(depotJpaRepository, mapper);
    }

    @Test void
    save_a_depot() {

        depotHibernateJpaRepository.save(aDepot());

        verify(depotJpaRepository).save(refEq(aDepotEntity()));
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
                .build();
    }
//
//    private Bus aBus() {
//        return Bus.BusBuilder.aBus()
//                .withId(1L)
//                .withPlateNumber("8711HHL")
//                .withBusType(BusType.REGULAR)
//                .withBusColor(Color.GREEN)
//                .withCapacity(50)
//                .build();
//    }
//
//    private BusEntity aBusEntity() {
//        return BusEntity.BusEntityBuilder.aBusEntity()
//                .withId(1L)
//                .withPlateNumber("8711HHL")
//                .withBusType(BusType.REGULAR)
//                .withBusColor(Color.GREEN)
//                .withCapacity(50)
//                .build();
//    }
}
