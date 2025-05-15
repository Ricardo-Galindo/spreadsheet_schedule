package unit.com.TimeSlotSpreadSheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unit.com.TimeSlotSpreadSheet.model.Teacher;
import unit.com.TimeSlotSpreadSheet.repository.TeacherRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository){this.teacherRepository = teacherRepository;}

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(UUID id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()){
            return teacher.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        return teacherRepository.findByEmail(email).map(teacher -> {
            teacher.setPassword(passwordEncoder.encode(newPassword));
            teacherRepository.save(teacher);
            return true;
        }).orElse(false);
    }


}
