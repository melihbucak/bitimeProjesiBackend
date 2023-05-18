package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.CourseDto;
import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourses")
    public ResponseEntity<List<Course>> getDersler() {
        List<Course> courseList = courseService.getDersler();
        return ResponseEntity.ok(courseList);
    }

    @PostMapping("/saveCourse")
    public ResponseEntity<Course> saveDers(@RequestBody Course course) {
        Course course1 = courseService.saveDers(course);
        return new ResponseEntity<>(course1, HttpStatus.CREATED);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity updateTeacher(@RequestBody CourseDto dto) {
        return courseService.updateCourseFromDto(dto);
    }

    @PostMapping("/deleteCourse")
    public boolean deleteStudent(@RequestBody CourseDto dto) {
        return courseService.deleteCourse(dto);
    }

    @GetMapping("/getCourseByTeacher/{id}")
    public List<Course> getCourseByTeacherId(@PathVariable("id") Long id) {
        return courseService.getCourseByTeacherId(id);
    }

}
