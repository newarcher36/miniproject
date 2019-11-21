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
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ParkBuses extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Inject
    private DepotJpaRepository depotJpaRepository;

    @Test void
    park_buses_to_a_given_depot() {

        busJpaRepository.save(aBusEntity());
        depotJpaRepository.save(anEmptyDepotEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .post("/v1/depots/{depotId}/{busIds}", 1L, aListOfBusIds())
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    private DepotEntity anEmptyDepotEntity() {
        return DepotEntity.DepotEntityBuilder.aDepotEntity()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
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

    private Set<Long> aListOfBusIds() {
        return Collections.singleton(1L);
    }
}
