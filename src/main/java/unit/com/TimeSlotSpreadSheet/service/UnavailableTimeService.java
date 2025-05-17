package unit.com.TimeSlotSpreadSheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unit.com.TimeSlotSpreadSheet.model.UnavailableTime;
import unit.com.TimeSlotSpreadSheet.repository.UnavailableTimeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UnavailableTimeService {

    @Autowired
    private UnavailableTimeRepository unavailableTimeRepository;

    public UnavailableTimeService(UnavailableTimeRepository unavailableTimeRepository) {this.unavailableTimeRepository = unavailableTimeRepository;}


    public UnavailableTime saveUnavailableTime(UnavailableTime unavailableTime) {
        return unavailableTimeRepository.save(unavailableTime);
    }

    public void deleteUnavailableTime(UUID id){
        if(unavailableTimeRepository.existsById(id)){
            unavailableTimeRepository.deleteById(id);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        }
    }

    public List<UnavailableTime> getAllUnavailableTimes() {
        List<UnavailableTime> unavailableTimes = unavailableTimeRepository.findAll();
        return unavailableTimes;
    }

}
