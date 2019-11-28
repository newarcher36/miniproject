package com.flixbus.miniproject.domain.depot;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.exception.DepotBusyException;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class Depot {

    private final Long id;
    private final String name;
    private final int capacity;
    private Set<Bus> buses;

    public Depot(Long id, String name, int capacity, Set<Bus> buses) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.buses = buses;
        validate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Bus> getBuses() {
        return new HashSet<>(buses);
    }

    public void add(Bus bus) {
        if (capacity == buses.size()) {
            throw new DepotBusyException(String.format("Cannot park more buses. Depot with id %d is busy", id));
        }
        this.buses.add(bus);
    }

    public void remove(Bus bus) {
        buses.remove(bus);
    }

    private void validate() {

        if (isNull(name) || name.trim().isEmpty()) {
            throwException("Depot name is required");
        }

        if (capacity <= 0) {
            throwException("Depot capacity is required");
        }

        if(isNull(buses)) {
            buses = new HashSet<>();
        }
    }

    private void throwException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static final class DepotBuilder {

        private Long id;
        private String name;
        private int capacity;
        private Set<Bus> buses;

        private DepotBuilder() {
        }

        public static DepotBuilder aDepot() {
            return new DepotBuilder();
        }

        public DepotBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public DepotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotBuilder withCapacity(int busCapacity) {
            this.capacity = busCapacity;
            return this;
        }

        public DepotBuilder withBuses(Set<Bus> buses) {
            this.buses = buses;
            return this;
        }

        public Depot build() {
            return new Depot(id, name, capacity, buses);
        }
    }
}
