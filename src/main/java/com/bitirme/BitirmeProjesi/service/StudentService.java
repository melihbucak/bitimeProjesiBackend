package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dtomapper.StudentDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentRepository;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;


    public Student saveOgrenci(Student student) {
        String plainPassword = student.getUser().getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        student.getUser().setPassword(hashedPassword);
        return studentRepository.save(student);
    }

    public Student getOgrenciById(Long id) {
        return studentRepository.getOne(id);
    }

    public List<Student> getOgrenciler() {
        return studentRepository.findAll();
    }

    public ResponseEntity updateStudentFromDto(StudentDto dto) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Student> oStudent = studentRepository.findById(dto.getId());
        if (oStudent.isPresent()) {
            Student student = oStudent.get();
            student.getUser().setUsername(dto.getOgrenci_no().toString());
            hm.put("status", true);
            hm.put("result", oStudent);
            StudentDtoMapper studentDtoMapper = Mappers.getMapper(StudentDtoMapper.class);
            studentDtoMapper.updateStudentFromDto(dto, student);
            studentRepository.save(student);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put("status", false);
        hm.put("message", "Update From Dto Failed");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }
    @Transactional
    public boolean deleteStudent(StudentDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getId());
        if (!student.isPresent()) {
            return false;
        }
        studentCourseRepository.deleteByStudent(student.get());
        userRepository.deleteById(dto.getUserid());
        studentRepository.deleteById(dto.getId());

        return true;
    }
}
