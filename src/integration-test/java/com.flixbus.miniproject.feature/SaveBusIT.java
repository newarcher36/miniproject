package com.flixbus.miniproject.feature;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.Bus.BusBuilder;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.BusJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import javax.inject.Inject;

import static com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity.BusEntityBuilder;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class SaveBusIT extends AbstractIT {

    private static final String PLATE_NUMBER = "8711HHL";

    @Inject
    private BusJpaRepository busJpaRepository;

    @Test void
    save_a_bus() {

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBus())
                .when()
                .post("/v1/buses")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_save_a_bus_with_duplicated_plate_number() {

        busJpaRepository.save(aBusEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBus())
                .when()
                .post("/v1/buses")
                .then()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_save_a_bus_without_plate_number() {

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusWithoutPlateNumber())
                .when()
                .post("/v1/buses")
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .statusCode();
    }

    private Bus aBus() {
        return BusBuilder.aBus()
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }

    private BusEntity aBusEntity() {
        return BusEntityBuilder.aBusEntity()
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
    private Bus aBusWithoutPlateNumber() {
        return BusBuilder.aBus()
                .withPlateNumber(null)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }

}
