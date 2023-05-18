package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.repo.CourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentRepository;
import com.bitirme.BitirmeProjesi.repo.YoklamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private YoklamaRepository yoklamaRepository;

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Object> addCourseToStudent(@PathVariable Long studentId, @RequestBody Course course1) {
        Long dersKodu = course1.getDersKodu();
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(dersKodu);
        Yoklama yoklama = new Yoklama();
        if (student.isPresent() && course.isPresent()) {
            StudentCourse studentCourse = new StudentCourse(student.get(), course.get());
            yoklama.setStudent(student.get());
            yoklama.setDersKodu(course.get());
            yoklama.setDevamsizlikSayisi(0);
            yoklama.setIzinSayisi(0);

            studentCourseRepository.save(studentCourse);
            yoklamaRepository.save(yoklama);
            return new ResponseEntity<>(studentCourse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student or course not found.", HttpStatus.NOT_FOUND);
        }
    }


}
