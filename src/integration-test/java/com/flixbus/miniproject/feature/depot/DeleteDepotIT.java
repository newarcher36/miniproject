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
import java.util.Set;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class DeleteDepotIT extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Inject
    private DepotJpaRepository depotJpaRepository;

    @Test void
    delete_a_depot_by_id() {

        depotJpaRepository.save(anEmptyDepotEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/depots/{depotId}", 1)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_delete_a_non_existing_depot() {

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/depots/{depotId}", 1)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_delete_a_depot_with_buses() {

        busJpaRepository.save(aBusEntity());
        depotJpaRepository.save(aDepotEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/depots/{depotId}", 1)
                .then()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .statusCode();
    }

    private DepotEntity anEmptyDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(Collections.emptySet())
                .build();
    }

    private DepotEntity aDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withCapacity(12)
                .withBuses(Set.of(aBusEntity()))
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
