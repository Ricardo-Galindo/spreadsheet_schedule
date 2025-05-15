package unit.com.TimeSlotSpreadSheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unit.com.TimeSlotSpreadSheet.model.Teacher;
import unit.com.TimeSlotSpreadSheet.model.TeacherDetails;
import unit.com.TimeSlotSpreadSheet.repository.TeacherRepository;

@Service
public class TeacherDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));
        return new TeacherDetails(teacher);
    }
}

