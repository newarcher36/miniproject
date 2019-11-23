package com.flixbus.miniproject.feature.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.feature.AbstractIT;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.bus.BusJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class ParkBusToDepotIT extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Inject
    private DepotJpaRepository depotJpaRepository;

    @SpyBean
    private DepotRepository depotRepository;

    @SpyBean
    private BusRepository busRepository;

    @Test void
    park_buses_to_a_given_depot() {

        // script?
        busJpaRepository.save(aBusEntity());
        depotJpaRepository.save(aDepotEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .post("/v1/depots/{depotId}/buses/{busIds}", 1L, 1L)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_add_a_bus_to_a_full_depot() {

        when(depotRepository.findDepotById(1L)).thenReturn(Optional.of(aFullDepot()));
        when(busRepository.findBusById(1L)).thenReturn(Optional.of(aBus()));
        when(busRepository.isBusParkedAlready(1L)).thenReturn(false);


        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .post("/v1/depots/{depotId}/buses/{busIds}", 1L, 1L)
                .then()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_a_bus_already_parked_on_another_depot() {

        when(depotRepository.findDepotById(1L)).thenReturn(Optional.of(aFullDepot()));
        when(busRepository.findBusById(1L)).thenReturn(Optional.of(aBus()));
        when(busRepository.isBusParkedAlready(1L)).thenReturn(true);


        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .post("/v1/depots/{depotId}/buses/{busIds}", 1L, 1L)
                .then()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .statusCode();
    }

    private Depot aFullDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(1)
                .withBuses(Set.of(aBus()))
                .build();
    }

    private DepotEntity aDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
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

    private Bus aBus() {
        return Bus.BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("1908IKH")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
