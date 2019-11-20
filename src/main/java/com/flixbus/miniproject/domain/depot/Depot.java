package com.flixbus.miniproject.domain.depot;

import com.flixbus.miniproject.domain.bus.Bus;

import java.util.Set;

public class Depot {

    private long id;
    private String name;
    private int busCapacity;
    private Set<Bus> buses;

    public Depot(long id, String name, int busCapacity, Set<Bus> buses) {
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

    public static final class DepotBuilder {

        private long id;
        private String name;
        private int busCapacity;
        private Set<Bus> buses;

        private DepotBuilder() {
        }

        public static DepotBuilder aDepot() {
            return new DepotBuilder();
        }

        public DepotBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DepotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotBuilder withBusCapacity(int busCapacity) {
            this.busCapacity = busCapacity;
            return this;
        }

        public DepotBuilder withBuses(Set<Bus> buses) {
            this.buses = buses;
            return this;
        }

        public Depot build() {
            return new Depot(id, name, busCapacity, buses);
        }
    }
}
