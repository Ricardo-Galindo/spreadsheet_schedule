package unit.com.TimeSlotSpreadSheet.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unit.com.TimeSlotSpreadSheet.model.Teacher;
import unit.com.TimeSlotSpreadSheet.repository.TeacherRepository;
import unit.com.TimeSlotSpreadSheet.service.TeacherService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        // Criptografa a senha antes de salvar
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        Teacher saved = teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable UUID id){
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email){
        Teacher teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @PutMapping("/{email}/password")
    public ResponseEntity<?> updatePassword(@PathVariable String email, @RequestBody Map<String, String> body) {
        String newPassword = body.get("newPassword");
        if (newPassword == null || newPassword.isBlank()) {
            return ResponseEntity.badRequest().body("Nova senha não pode ser vazia");
        }

        boolean updated = teacherService.updatePassword(email, newPassword);

        if (updated) {
            return ResponseEntity.ok("Senha atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable UUID id){
        teacherService.deleteTeacherById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor com ID: " + id + " foi excluído com sucesso!");
    }

}
