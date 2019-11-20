package com.flixbus.miniproject.feature.depot;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.feature.AbstractIT;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class SaveDepotIT extends AbstractIT {

    @Test void
    save_a_depot() {

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aDepotWriteDto())
                .when()
                .post("/v1/depots")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    private DepotWriteDto aDepotWriteDto() {
        return DepotWriteDto.DepotWriteDtoBuilder.aDepotWriteDto()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }
}
