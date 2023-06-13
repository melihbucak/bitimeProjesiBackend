package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentRepository;
import com.bitirme.BitirmeProjesi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentRepository studentRepository;

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
    public ResponseEntity updateStudent(@RequestBody StudentDto dto) {
        return studentService.updateStudentFromDto(dto);
    }

    @PostMapping("/deleteStudent")
    public boolean deleteStudent(@RequestBody StudentDto dto) {
        return studentService.deleteStudent(dto);
    }

    @GetMapping("/students/{id}/courses")
    public List<Course> getStudentCourses(@PathVariable Long id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        List<Course> courses = new ArrayList<>();
        List<StudentCourse> studentCourses = studentCourseRepository.findByStudent(student);
        for (StudentCourse studentCourse : studentCourses) {
            courses.add(studentCourse.getCourse());
        }
        return courses;
    }
}
