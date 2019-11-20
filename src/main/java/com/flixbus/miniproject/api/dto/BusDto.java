package com.flixbus.miniproject.api.dto;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BusDto {

    private final long id;
    private final String plateNumber;
    private final BusType busType;
    private final Color busColor;
    private final int capacity;

    public BusDto(Long id, String plateNumber, BusType busType, Color busColor, int capacity) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.busType = busType;
        this.busColor = busColor;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public BusType getBusType() {
        return busType;
    }

    public Color getBusColor() {
        return busColor;
    }

    public int getCapacity() {
        return capacity;
    }

    public static final class BusDtoBuilder {

        private long id;
        private String plateNumber;
        private BusType busType;
        private Color busColor;
        private int capacity;

        private BusDtoBuilder() {
        }

        public static BusDtoBuilder aBusDto() {
            return new BusDtoBuilder();
        }

        public BusDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BusDtoBuilder withPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
            return this;
        }

        public BusDtoBuilder withBusType(BusType busType) {
            this.busType = busType;
            return this;
        }

        public BusDtoBuilder withBusColor(Color busColor) {
            this.busColor = busColor;
            return this;
        }

        public BusDtoBuilder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public BusDto build() {
            return new BusDto(id, plateNumber, busType, busColor, capacity);
        }
    }
}
