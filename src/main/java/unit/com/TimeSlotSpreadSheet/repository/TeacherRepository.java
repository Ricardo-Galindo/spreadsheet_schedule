package unit.com.TimeSlotSpreadSheet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import unit.com.TimeSlotSpreadSheet.model.Teacher;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Optional<Teacher> findByEmail(String email);
}
