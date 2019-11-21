package com.flixbus.miniproject.api.dto;

import java.util.Objects;

public class DepotWriteDto {

    private Long id;
    private String name;
    private int busCapacity;

    public DepotWriteDto(Long id, String name, int busCapacity) {
        this.id = id;
        this.name = name;
        this.busCapacity = busCapacity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepotWriteDto that = (DepotWriteDto) o;
        return id == that.id &&
                busCapacity == that.busCapacity &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, busCapacity);
    }

    public static final class DepotWriteDtoBuilder {

        private Long id;
        private String name;
        private int busCapacity;

        private DepotWriteDtoBuilder() {
        }

        public static DepotWriteDtoBuilder aDepotWriteDto() {
            return new DepotWriteDtoBuilder();
        }

        public DepotWriteDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public DepotWriteDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepotWriteDtoBuilder withBusCapacity(int busCapacity) {
            this.busCapacity = busCapacity;
            return this;
        }

        public DepotWriteDto build() {
            return new DepotWriteDto(id, name, busCapacity);
        }
    }
}
