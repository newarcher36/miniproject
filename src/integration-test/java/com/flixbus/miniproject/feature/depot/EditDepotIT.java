package com.flixbus.miniproject.feature.depot;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.DepotRepository;
import com.flixbus.miniproject.feature.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class EditDepotIT extends AbstractIT {

    @SpyBean
    private DepotRepository depotRepository;

    @Test void
    edit_a_depot() {

        when(depotRepository.existsById(1L)).thenReturn(true);

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

    private DepotWriteDto aDepotWriteDto() {
        return DepotWriteDto.DepotWriteDtoBuilder.aDepotWriteDto()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }
}
