package com.flixbus.miniproject.domain.bus;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public enum Color {
    GREEN,
    ORANGE;

    private static Map<String, Color> colors;

    static {
        colors = Stream.of(Color.values())
                .collect(Collectors.toMap(Color::name, color -> color));
    }

    public static Color resolve(String color) {

        Color busColor = colors.get(color.toUpperCase());

        if (isNull(busColor)) {
            throw new IllegalArgumentException(String.format("Bus color $s does not exist",color));
        }

        return busColor;
    }
}
