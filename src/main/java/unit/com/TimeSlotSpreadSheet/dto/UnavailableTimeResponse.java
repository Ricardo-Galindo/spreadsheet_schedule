package unit.com.TimeSlotSpreadSheet.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UnavailableTimeResponse(UUID id,UUID teacher_id, int day, String timeSlot, LocalDateTime createdAt){}
