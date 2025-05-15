package unit.com.TimeSlotSpreadSheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unit.com.TimeSlotSpreadSheet.model.UnavailableTime;
import unit.com.TimeSlotSpreadSheet.repository.UnavailableTimeRepository;

@Service
public class UnavailableTimeService {

    @Autowired
    private UnavailableTimeRepository unavailableTimeRepository;

    public UnavailableTimeService(UnavailableTimeRepository unavailableTimeRepository) {this.unavailableTimeRepository = unavailableTimeRepository;}


    public UnavailableTime saveUnavailableTime(UnavailableTime unavailableTime) {
        return unavailableTimeRepository.save(unavailableTime);
    }


}
