package unit.com.TimeSlotSpreadSheet.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unit.com.TimeSlotSpreadSheet.dto.UnavailableTimeRequest;
import unit.com.TimeSlotSpreadSheet.dto.UnavailableTimeResponse;
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
    public ResponseEntity<List<UnavailableTimeResponse>> getAllUnavailableTimes(){
        List<UnavailableTimeResponse> unavailableTimes = unavailableTimeService.getAllUnavailableTimes();
        return ResponseEntity.status(HttpStatus.OK).body(unavailableTimes);
    }

    @PostMapping
    public ResponseEntity<UnavailableTimeResponse> createUnavailableTime(@Valid @RequestBody UnavailableTimeRequest unavailableTimeRequest){
        UnavailableTime newUnavailableTime = unavailableTimeService.saveUnavailableTime(unavailableTimeRequest);
        UnavailableTimeResponse response = unavailableTimeService.convertToDTO(newUnavailableTime);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnavailableTime(@PathVariable UUID id){
        unavailableTimeService.deleteUnavailableTime(id);
        return ResponseEntity.status(HttpStatus.OK).body("Horário com ID: " + id + " foi excluído com sucesso!");
    }


}
