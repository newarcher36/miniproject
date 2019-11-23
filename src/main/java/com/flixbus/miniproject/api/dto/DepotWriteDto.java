package com.flixbus.miniproject.api.dto;

public class DepotWriteDto {

    private Long id;
    private String name;
    private int capacity;

    public DepotWriteDto(Long id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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
