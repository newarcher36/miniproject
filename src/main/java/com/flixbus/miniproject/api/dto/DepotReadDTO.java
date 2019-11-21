package com.flixbus.miniproject.api.dto;

import com.flixbus.miniproject.domain.bus.Bus;

import java.util.Set;

public class DepotReadDTO {

    private long id;
    private String name;
    private int busCapacity;
    private Set<Bus> buses;

    public DepotReadDTO(long id, String name, int busCapacity, Set<Bus> buses) {
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

    public Set<Bus> getBuses() {
        return buses;
    }


    public static final class DepotReadDTOBuilder {
        private long id;
        private String name;
        private int busCapacity;
        private Set<Bus> buses;

        private DepotReadDTOBuilder() {
        }

        public static DepotReadDTOBuilder aDepotReadDTO() {
            return new DepotReadDTOBuilder();
        }

        public DepotReadDTOBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DepotReadDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotReadDTOBuilder withBusCapacity(int busCapacity) {
            this.busCapacity = busCapacity;
            return this;
        }

        public DepotReadDTOBuilder withBuses(Set<Bus> buses) {
            this.buses = buses;
            return this;
        }

        public DepotReadDTO build() {
            return new DepotReadDTO(id, name, busCapacity, buses);
        }
    }
}
