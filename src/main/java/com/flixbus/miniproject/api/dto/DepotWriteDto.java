package com.flixbus.miniproject.api.dto;

public class DepotWriteDto {

    private long id;
    private String name;
    private int busCapacity;

    public DepotWriteDto(long id, String name, int busCapacity) {
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

    public static final class DepotWriteDtoBuilder {

        private long id;
        private String name;
        private int busCapacity;

        private DepotWriteDtoBuilder() {
        }

        public static DepotWriteDtoBuilder aDepotWriteDto() {
            return new DepotWriteDtoBuilder();
        }

        public DepotWriteDtoBuilder withId(long id) {
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
