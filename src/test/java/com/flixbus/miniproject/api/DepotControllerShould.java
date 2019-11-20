package com.flixbus.miniproject.api;

import com.flixbus.miniproject.api.dto.DepotWriteDto;
import com.flixbus.miniproject.domain.depot.Depot;
import com.flixbus.miniproject.usecase.EditDepot;
import com.flixbus.miniproject.usecase.SaveDepot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class DepotControllerShould {

    private DepotController depotController;

    @Mock
    private SaveDepot saveDepot;

    @Mock
    private EditDepot editDepot;

    @BeforeEach
    void init() {
        depotController = new DepotController(saveDepot, editDepot);
    }

    @Test void
    save_a_depot() {

        DepotWriteDto depotWriteDto = aDepotWritetDto();

        depotController.save(depotWriteDto);

        verify(saveDepot).execute(refEq(aDepot()));
    }
    
    @Test void
    edit_a_depot() {

        DepotWriteDto depotWriteDto = aDepotWritetDto();

        depotController.edit(depotWriteDto);

        verify(editDepot).execute(refEq(aDepot()));
    }

    private DepotWriteDto aDepotWritetDto() {
        return DepotWriteDto.DepotWriteDtoBuilder.aDepotWriteDto()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }

    private Depot aDepot() {
        return Depot.DepotBuilder.aDepot()
                .withId(1L)
                .withName("Bavaria")
                .withBusCapacity(12)
                .build();
    }

//    private BusDto aBusDto() {
//        return BusDto.BusDtoBuilder.aBusDto()
//                .withId(1L)
//                .withPlateNumber("8711HHL")
//                .withBusType(BusType.REGULAR)
//                .withBusColor(Color.GREEN)
//                .withCapacity(50)
//                .build();
//    }
//
//    private Bus aBus() {
//        return Bus.BusBuilder.aBus()
//                .withId(1L)
//                .withPlateNumber("8711HHL")
//                .withBusType(BusType.REGULAR)
//                .withBusColor(Color.GREEN)
//                .withCapacity(50)
//                .build();
//    }
}
