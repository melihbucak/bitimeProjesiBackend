package com.bitirme.BitirmeProjesi.controller;

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
//    @GetMapping("/saveDeneme/{dersAdi}/{ogretmenId}")
//    public ResponseEntity<Dersler> saveDeneme(@PathVariable("dersAdi") String dersAdi, @PathVariable("ogretmenId") Long ogretmenId) {
//        Dersler dersler = new Dersler();
//        dersler.setDersAdi(dersAdi);
//        dersler.setOgretmen(teacherRepository.getById(ogretmenId));
//        Dersler dersler1 = userService.saveDers(dersler);
//        return new ResponseEntity<>(dersler1, HttpStatus.CREATED);
//    }
}
