package unit.com.TimeSlotSpreadSheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unit.com.TimeSlotSpreadSheet.dto.UnavailableTimeRequest;
import unit.com.TimeSlotSpreadSheet.dto.UnavailableTimeResponse;
import unit.com.TimeSlotSpreadSheet.model.UnavailableTime;
import unit.com.TimeSlotSpreadSheet.repository.TeacherRepository;
import unit.com.TimeSlotSpreadSheet.repository.UnavailableTimeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UnavailableTimeService {

    @Autowired
    private UnavailableTimeRepository unavailableTimeRepository;

    @Autowired
    private TeacherService teacherService;

    public UnavailableTimeService(UnavailableTimeRepository unavailableTimeRepository) {this.unavailableTimeRepository = unavailableTimeRepository;}


    public UnavailableTimeResponse convertToDTO(UnavailableTime unavailableTime){
       return new UnavailableTimeResponse(
               unavailableTime.getId(),
                unavailableTime.getTeacher().getId(),
                unavailableTime.getDay(),
                unavailableTime.getTimeSlot(),
               unavailableTime.getCreatedAt()
        );
    }

    public UnavailableTime getUnavailableTimeById(UUID id){
        Optional<UnavailableTime> unavailableTime = unavailableTimeRepository.findById(id);

        if(unavailableTime.isPresent()){
            return unavailableTime.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        }

    }


    public UnavailableTime saveUnavailableTime(UnavailableTimeRequest unavailableTimeRequest) {
        var teacher = teacherService.getTeacherById(unavailableTimeRequest.teacher_id());
        var newUnavailableTime = new UnavailableTime();
        newUnavailableTime.setTeacher(teacher);
        newUnavailableTime.setDay(unavailableTimeRequest.day());
        newUnavailableTime.setTimeSlot(unavailableTimeRequest.timeSlot());
        newUnavailableTime.setCreatedAt(unavailableTimeRequest.createdAt());

        return unavailableTimeRepository.save(newUnavailableTime);
    }

    public void deleteUnavailableTime(UUID id){
        if(unavailableTimeRepository.existsById(id)){
            unavailableTimeRepository.deleteById(id);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        }
    }

    public List<UnavailableTimeResponse> getAllUnavailableTimes() {
        List<UnavailableTime> unavailableTimes = unavailableTimeRepository.findAll();
        return unavailableTimes.stream().map(unavailableTime -> convertToDTO(unavailableTime)).toList();
    }

}
