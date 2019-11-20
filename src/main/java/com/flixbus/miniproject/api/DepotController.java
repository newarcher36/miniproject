package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.usecase.EditDepot;
import com.flixbus.miniproject.usecase.SaveDepot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.flixbus.miniproject.domain.depot.Depot.DepotBuilder;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/depots")
public class DepotController {

    private final SaveDepot saveDepot;
    private final EditDepot editDepot;

    public DepotController(SaveDepot saveDepot, EditDepot editDepot) {
        this.saveDepot = saveDepot;
        this.editDepot = editDepot;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void save(@RequestBody DepotWriteDto depotWriteDto) {
        Depot depot = mapToDepot(depotWriteDto);
        saveDepot.execute(depot);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void edit(@RequestBody DepotWriteDto depotWriteDto) {
        Depot depot = mapToDepot(depotWriteDto);
        editDepot.execute(depot);
    }

    private Depot mapToDepot(DepotWriteDto depotWriteDto) {
        return DepotBuilder.aDepot()
                .withId(depotWriteDto.getId())
                .withName(depotWriteDto.getName())
                .withBusCapacity(depotWriteDto.getBusCapacity())
                .build();
    }
//
//    private Set<Bus> mapToBuses(Set<BusDto> busDtos) {
//        return busDtos.stream().map(mapper::map).collect(Collectors.toSet());
//    }
}
