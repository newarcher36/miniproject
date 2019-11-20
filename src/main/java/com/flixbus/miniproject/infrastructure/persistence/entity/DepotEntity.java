package com.flixbus.miniproject.infrastructure.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "depot")
public class DepotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int busCapacity;

    @OneToMany
    private Set<BusEntity> buses;

    public DepotEntity() {}

    public DepotEntity(long id, String name, int busCapacity, Set<BusEntity> buses) {
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

    public Set<BusEntity> getBuses() {
        return buses;
    }

    public static final class DepotEntityBuilder {
        private long id;
        private String name;
        private int busCapacity;
        private Set<BusEntity> buses;

        private DepotEntityBuilder() {
        }

        public static DepotEntityBuilder aDepotEntity() {
            return new DepotEntityBuilder();
        }

        public DepotEntityBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DepotEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotEntityBuilder withBusCapacity(int busCapacity) {
            this.busCapacity = busCapacity;
            return this;
        }

        public DepotEntityBuilder withBuses(Set<BusEntity> buses) {
            this.buses = buses;
            return this;
        }

        public DepotEntity build() {
            return new DepotEntity(id, name, busCapacity, buses);
        }
    }
}
