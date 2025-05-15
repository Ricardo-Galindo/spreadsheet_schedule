package unit.com.TimeSlotSpreadSheet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import unit.com.TimeSlotSpreadSheet.model.UnavailableTime;

import java.util.UUID;

public interface UnavailableTimeRepository extends JpaRepository<UnavailableTime, UUID> {
}
