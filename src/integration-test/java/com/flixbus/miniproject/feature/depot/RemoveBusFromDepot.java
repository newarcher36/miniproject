package com.flixbus.miniproject.feature.depot;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.feature.AbstractIT;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.bus.BusJpaRepository;
import com.flixbus.miniproject.infrastructure.persistence.repository.depot.DepotJpaRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class RemoveBusFromDepot extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Inject
    private DepotJpaRepository depotJpaRepository;

    @Test
    void
    remove_a_bus_from_a_given_depot() {

        busJpaRepository.save(aBusEntity());
        depotJpaRepository.save(aDepotEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/depots/{depotId}/buses/{busIds}", 1L, 1L)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    private DepotEntity aDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(Collections.emptySet())
                .build();
    }

    private BusEntity aBusEntity() {
        return BusEntity.BusEntityBuilder.aBusEntity()
                .withId(1L)
                .withPlateNumber("BUS-111-111")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
