package com.flixbus.miniproject.domain.bus;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public enum BusType {

    REGULAR,
    DOUBLE_DECKER;

    private static Map<String, BusType> busTypes;

    static {
        busTypes = Stream.of(BusType.values())
                .collect(Collectors.toMap(BusType::name, busType -> busType));
    }

    public static BusType resolve(String type) {

        BusType busType = busTypes.get(type.toUpperCase());

        if (isNull(busType)) {
            throw new IllegalArgumentException("Bus type not found");
        }

        return busType;
    }
}
