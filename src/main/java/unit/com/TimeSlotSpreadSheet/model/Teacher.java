package unit.com.TimeSlotSpreadSheet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true)
    @NotNull
    private int matricula;

    @CPF
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false,unique = true)
    @Email()
    @NotBlank()
    private String email;

    @Column(nullable = false,length = 30)
    @NotBlank()
    private String password;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnavailableTime> unavailableTimes = new ArrayList<>();
}
