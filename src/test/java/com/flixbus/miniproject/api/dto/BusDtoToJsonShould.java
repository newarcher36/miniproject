package com.flixbus.miniproject.api.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BusDtoToJsonShould {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test void
    serialize_bus_dto_to_json() throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(aBusDto());
        String expected = "{\"id\":1,\"plateNumber\":\"8711HHL\",\"busType\":\"REGULAR\",\"busColor\":\"GREEN\",\"capacity\":50}";

        Assertions.assertThat(result)
                .isEqualTo(expected);
    }

    private BusDto aBusDto() {
        return BusDto.BusDtoBuilder.aBusDto()
                .withId(1L)
                .withPlateNumber("8711HHL")
                .withBusType(BusType.REGULAR)
                .withBusColor(Color.GREEN)
                .withCapacity(50)
                .build();
    }
}
