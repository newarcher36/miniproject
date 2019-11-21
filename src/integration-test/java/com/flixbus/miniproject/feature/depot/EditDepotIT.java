package com.flixbus.miniproject.feature.depot;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.feature.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Collections.emptySet;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class EditDepotIT extends AbstractIT {

    @SpyBean
    private DepotRepository depotRepository;

    @Test void
    edit_a_depot() {

        when(depotRepository.findDepotById(1L)).thenReturn(Optional.of(aDepot()));

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aDepotWriteDto())
                .when()
                .put("/v1/depots")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    @Test void
    fail_when_edit_a_non_existing_depot() {

        when(depotRepository.findDepotById(1L)).thenReturn(Optional.empty());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(aDepotWriteDto())
                .when()
                .put("/v1/depots")
                .then()
                .statusCode(HTTP_NOT_FOUND)
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

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .withBuses(emptySet())
                .build();
    }
}
