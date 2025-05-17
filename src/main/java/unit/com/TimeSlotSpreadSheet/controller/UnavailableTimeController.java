package unit.com.TimeSlotSpreadSheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unit.com.TimeSlotSpreadSheet.model.UnavailableTime;
import unit.com.TimeSlotSpreadSheet.service.UnavailableTimeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/unavailable_time")
public class UnavailableTimeController {

    private final UnavailableTimeService unavailableTimeService;

    public UnavailableTimeController(UnavailableTimeService unavailableTimeService){
        this.unavailableTimeService = unavailableTimeService;
    }

    @GetMapping
    public ResponseEntity<List<UnavailableTime>> getAllUnavailableTimes(){
        List<UnavailableTime> unavailableTimes = unavailableTimeService.getAllUnavailableTimes();
        return ResponseEntity.status(HttpStatus.OK).body(unavailableTimes);
    }

    @PostMapping
    public ResponseEntity<UnavailableTime> createUnavailableTime(@RequestBody UnavailableTime unavailableTime){
        UnavailableTime newUnavailableTime = unavailableTimeService.saveUnavailableTime(unavailableTime);
        return ResponseEntity.status(HttpStatus.OK).body(newUnavailableTime);
    }



    @DeleteMapping
    public ResponseEntity<String> deleteUnavailableTime(UUID id){
        unavailableTimeService.deleteUnavailableTime(id);
        return ResponseEntity.status(HttpStatus.OK).body("Horário com ID: " + id + " foi excluído com sucesso!");
    }


}
