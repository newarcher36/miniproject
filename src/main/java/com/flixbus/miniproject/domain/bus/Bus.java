package com.flixbus.miniproject.domain.bus;

import lombok.EqualsAndHashCode;

import static java.util.Objects.isNull;

@EqualsAndHashCode
public class Bus {

    private final Long id;
    private final String plateNumber;
    private final BusType busType;
    private final Color busColor;
    private final int passengerCapacity;

    private Bus(Long id, String plateNumber, BusType busType, Color busColor, int passengerCapacity) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.busType = busType;
        this.busColor = busColor;
        this.passengerCapacity = passengerCapacity;
        validate();
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

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    private void validate() {
        if (isNull(plateNumber) || plateNumber.trim().isEmpty()) {
            throwException("Plate number is required");
        }

        if (!plateNumber.trim().matches("BUS-\\d{3}-\\d{3}$")) {
            throwException(String.format("Invalid bus plate number %s must have the following format: BUS-XXX-XXX",plateNumber));
        }

        if (isNull(busType)) {
            throwException("Bus type is required");
        }

        if (isNull(busColor)) {
            throwException("Bus color is required");
        }

        if (passengerCapacity < 0 || passengerCapacity > 70) {
            throwException("Bus capacity must be between 0 and 70 people");
        }
    }

    private void throwException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static final class BusBuilder {

        private Long id;
        private String plateNumber;
        private BusType type;
        private Color busColor;
        private int capacity;

        private BusBuilder() {
        }

        public static BusBuilder aBus() {
            return new BusBuilder();
        }

        public BusBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BusBuilder withPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
            return this;
        }

        public BusBuilder withBusType(BusType type) {
            this.type = type;
            return this;
        }

        public BusBuilder withBusColor(Color busColor) {
            this.busColor = busColor;
            return this;
        }

        public BusBuilder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Bus build() {
            return new Bus(id, plateNumber, type, busColor, capacity);
        }
    }
}
