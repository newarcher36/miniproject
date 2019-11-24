package com.flixbus.miniproject.feature.bus;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.feature.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


class SaveBusIT extends AbstractIT {

    private static final String PLATE_NUMBER = "BUS-111-111";

    @SpyBean
    private BusRepository busRepository;

    @Test void
    save_a_bus() {

        given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusDto())
                .when()
                .post("/v1/buses")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_save_a_bus_with_duplicated_plate_number() {

        when(busRepository.existsByPlateNumber("BUS-111-111")).thenReturn(true);

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusDto())
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
                .body(aBusDtoWithoutPlateNumber())
                .when()
                .post("/v1/buses")
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .statusCode();
    }

    private BusDto aBusDto() {
        return BusDto.BusDtoBuilder.aBusDto()
                .withPlateNumber(PLATE_NUMBER)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }

    private BusDto aBusDtoWithoutPlateNumber() {
        return BusDto.BusDtoBuilder.aBusDto()
                .withPlateNumber(null)
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }

}
