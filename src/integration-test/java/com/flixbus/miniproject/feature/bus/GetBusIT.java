package com.flixbus.miniproject.feature.bus;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.feature.AbstractIT;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.bus.BusJpaRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class GetBusIT extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Test void
    find_a_bus_by_id() {

        busJpaRepository.save(aBusEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/v1/buses/{busId}", 1)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_bus_not_found_by_id() {
        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/v1/buses/{busId}", 1)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .statusCode();
    }

    private BusEntity aBusEntity() {
        return BusEntity.BusEntityBuilder.aBusEntity()
                .withPlateNumber("BUS-111-111")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
