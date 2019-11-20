package com.flixbus.miniproject.api.dto;

import java.util.Set;

public class DepotDto {

    private long id;
    private String name;
    private int busCapacity;
    private Set<BusDto> buses;

    public DepotDto(long id, String name, int busCapacity, Set<BusDto> buses) {
        this.id = id;
        this.name = name;
        this.busCapacity = busCapacity;
        this.buses = buses;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBusCapacity() {
        return busCapacity;
    }

    public Set<BusDto> getBuses() {
        return buses;
    }

    public static final class DepotDtoBuilder {

        private long id;
        private String name;
        private int busCapacity;
        private Set<BusDto> buses;

        private DepotDtoBuilder() {
        }

        public static DepotDtoBuilder aDepotDto() {
            return new DepotDtoBuilder();
        }

        public DepotDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DepotDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotDtoBuilder withBusCapacity(int busCapacity) {
            this.busCapacity = busCapacity;
            return this;
        }

        public DepotDtoBuilder withBuses(Set<BusDto> buses) {
            this.buses = buses;
            return this;
        }

        public DepotDto build() {
            return new DepotDto(id, name, busCapacity, buses);
        }
    }
}
