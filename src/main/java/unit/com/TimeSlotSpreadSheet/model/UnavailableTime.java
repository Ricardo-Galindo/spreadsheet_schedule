package unit.com.TimeSlotSpreadSheet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Unavailable_times")
@Data
public class UnavailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "teacher_id",nullable = false,referencedColumnName = "id")
    private Teacher teacher;

    @Column(nullable = false)
    @NotNull
    private int day;

    @Column(name = "time_slot",nullable = false,length = 20)
    @NotBlank
    private String timeSlot;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
