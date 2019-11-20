package com.flixbus.miniproject.feature;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.repository.BusJpaRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class DeleteBusIT extends AbstractIT {

    @Inject
    private BusJpaRepository busJpaRepository;

    @Test
    void
    delete_a_bus_by_id() {

        busJpaRepository.save(aBusEntity());

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/v1/buses/{busId}", 1)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .statusCode();
    }

    private BusEntity aBusEntity() {
        return BusEntity.BusEntityBuilder.aBusEntity()
                .withPlateNumber("1908IKH")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}

