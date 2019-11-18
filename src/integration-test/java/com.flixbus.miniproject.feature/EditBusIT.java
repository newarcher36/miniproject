package com.flixbus.miniproject.feature;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class EditBusIT extends AbstractIT {

    @Test void
    edit_an_existing_bus() {
        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBus())
                .when()
                .put("/v1/buses")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    private Bus aBus() {
        return Bus.BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
