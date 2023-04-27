package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getDersler() {
        return courseRepository.findAll();
    }

    public Course saveDers(Course course) {
        return courseRepository.save(course);
    }
}
