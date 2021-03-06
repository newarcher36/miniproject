package com.flixbus.miniproject.infrastructure.persistence.entity;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity(name = "bus")
@EqualsAndHashCode
public class BusEntity extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String plateNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color busColor;

    @Max(70)
    @Column(nullable = false)
    private int passengerCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depot_id")
    private DepotEntity depot;

    public BusEntity(){}

    private BusEntity(Long id, String plateNumber, BusType busType, Color busColor, int passengerCapacity) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.busType = busType;
        this.busColor = busColor;
        this.passengerCapacity = passengerCapacity;
    }

    public void setDepot(DepotEntity depot) {
        this.depot = depot;
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

    public static final class BusEntityBuilder {
        private Long id;
        private String plateNumber;
        private BusType busType;
        private Color busColor;
        private int capacity;

        private BusEntityBuilder() {
        }

        public static BusEntityBuilder aBusEntity() {
            return new BusEntityBuilder();
        }

        public BusEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BusEntityBuilder withPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
            return this;
        }

        public BusEntityBuilder withBusType(BusType busType) {
            this.busType = busType;
            return this;
        }

        public BusEntityBuilder withBusColor(Color busColor) {
            this.busColor = busColor;
            return this;
        }

        public BusEntityBuilder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public BusEntity build() {
            return new BusEntity(id, plateNumber, busType, busColor, capacity);
        }
    }
}
