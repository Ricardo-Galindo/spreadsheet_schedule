package unit.com.TimeSlotSpreadSheet.dto;

import java.util.List;
import java.util.UUID;

public record TeacherResponse(UUID id,
                              int matricula,
                              String cpf,
                              String email,
                              List<UnavailableTimeResponse> unavailableTimes){}
