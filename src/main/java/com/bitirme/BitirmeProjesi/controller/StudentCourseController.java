package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.entity.*;
import com.bitirme.BitirmeProjesi.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/studentCourse")
public class StudentCourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Object> addCourseToStudent(@PathVariable Long studentId, @RequestBody Map<String, Long> request) {
        Long ders_Kodu = request.get("ders_Kodu");
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(ders_Kodu);

        if (student.isPresent() && course.isPresent()) {
            StudentCourse studentCourse = new StudentCourse(student.get(), course.get());
            studentCourseRepository.save(studentCourse);

            return new ResponseEntity<>(studentCourse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student or course not found.", HttpStatus.NOT_FOUND);
        }
    }

}
