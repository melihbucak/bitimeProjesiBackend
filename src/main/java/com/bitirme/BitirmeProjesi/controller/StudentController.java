package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/ogrenciListeleById/{id}")
    public ResponseEntity<Student> getOgrenciById(@PathVariable("id") Long id) {
        Student student = studentService.getOgrenciById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveOgrenci(@RequestBody Student student) {
        Student student1 = studentService.saveOgrenci(student);

        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        List<Student> studentList = studentService.getOgrenciler();
        return studentList;
    }
    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestBody StudentDto dto){
        return studentService.updateStudentFromDto(dto);
    }
}
