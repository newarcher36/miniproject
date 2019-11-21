package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.usecase.depot.EditDepot;
import com.flixbus.miniproject.usecase.depot.SaveDepot;
import com.flixbus.miniproject.usecase.depot.DeleteDepot;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final DeleteDepot deleteDepot;

    public DepotController(SaveDepot saveDepot, EditDepot editDepot, DeleteDepot deleteDepot) {
        this.saveDepot = saveDepot;
        this.editDepot = editDepot;
        this.deleteDepot = deleteDepot;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void createDepot(@RequestBody DepotWriteDto depotWriteDto) {
        Depot depot = mapToDepot(depotWriteDto);
        saveDepot.execute(depot);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void editDepot(@RequestBody DepotWriteDto depotWriteDto) {
        Depot depot = mapToDepot(depotWriteDto);
        editDepot.execute(depot);
    }

    @DeleteMapping(value = "{depotId}")
    public void deleteDepotById(@PathVariable long depotId) {
        deleteDepot.execute(depotId);
    }

    private Depot mapToDepot(DepotWriteDto depotWriteDto) {
        return DepotBuilder.aDepot()
                .withId(depotWriteDto.getId())
                .withName(depotWriteDto.getName())
                .withBusCapacity(depotWriteDto.getBusCapacity())
                .build();
    }
}
