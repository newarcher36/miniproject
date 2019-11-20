package com.flixbus.miniproject.feature;

import com.flixbus.miniproject.api.dto.BusDto;
import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static com.flixbus.miniproject.api.dto.BusDto.BusDtoBuilder;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class EditBusIT extends AbstractIT {

    @SpyBean
    private BusRepository busRepository;

    @Test void
    edit_an_existing_bus() {

        when(busRepository.findByBusId(1L)).thenReturn(aBus());
        when(busRepository.existsByPlateNumber("8711HHL")).thenReturn(false);

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusDto())
                .when()
                .put("/v1/buses")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_edit_a_non_existing_bus() {

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusDto())
                .when()
                .put("/v1/buses")
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_edit_a_bus_with_duplicated_plate_number() {

        when(busRepository.findByBusId(1L)).thenReturn(aBus());
        when(busRepository.existsByPlateNumber("5721HHL")).thenReturn(true);

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aBusDto())
                .when()
                .put("/v1/buses")
                .then()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .statusCode();
    }

    private Optional<Bus> aBus() {
        return Optional.of(Bus.BusBuilder.aBus()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.DOUBLE_DECKER)
                .withBusColor(Color.ORANGE)
                .withCapacity(60)
                .build());
    }

    private BusDto aBusDto() {
        return BusDtoBuilder.aBusDto()
                .withId(1L)
                .withPlateNumber("5721HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
