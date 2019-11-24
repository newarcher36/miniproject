package com.flixbus.miniproject.usecase.bus;

import com.flixbus.miniproject.domain.bus.Bus;
import com.flixbus.miniproject.domain.bus.BusRepository;
import com.flixbus.miniproject.domain.bus.spec.SearchCriteria;

import javax.inject.Named;
import java.util.List;

@Named
public class FilterBuses {

    private BusRepository busRepository;

    public FilterBuses(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> execute(SearchCriteria criteria) {
        return busRepository.findAllById(criteria);
    }
}
