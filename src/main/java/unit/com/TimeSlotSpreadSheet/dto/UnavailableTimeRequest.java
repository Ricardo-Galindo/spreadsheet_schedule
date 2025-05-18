package unit.com.TimeSlotSpreadSheet.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


public record UnavailableTimeRequest(UUID teacher_id, int day, String timeSlot, LocalDateTime createdAt){}
