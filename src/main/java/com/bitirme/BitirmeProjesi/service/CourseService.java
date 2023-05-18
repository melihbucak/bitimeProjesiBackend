package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.CourseDto;
import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.dtomapper.CourseDtoMapper;
import com.bitirme.BitirmeProjesi.dtomapper.TeacherDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.repo.CourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<Course> getDersler() {
        return courseRepository.findAll();
    }

    public Course saveDers(Course course) {
        return courseRepository.save(course);
    }

    public ResponseEntity updateCourseFromDto(CourseDto dto) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Course> oCourse = courseRepository.findById(dto.getDersKodu());
        if (oCourse.isPresent()) {
            Course course = oCourse.get();
            hm.put("status", true);
            hm.put("result", oCourse);
            CourseDtoMapper courseDtoMapper = Mappers.getMapper(CourseDtoMapper.class);
            courseDtoMapper.updateCourseFromDto(dto, course);
            courseRepository.save(course);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put("status", false);
        hm.put("message", "Kurs güncelleme başarısız");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public boolean deleteCourse(CourseDto dto) {
        Optional<Course> oCourse = courseRepository.findById(dto.getDersKodu());
        if (!oCourse.isPresent()) {
            return false;
        }
        studentCourseRepository.deleteByCourse(oCourse.get());
        courseRepository.deleteById(dto.getDersKodu());
        return true;
    }

    public List<Course> getCourseByTeacherId(Long id){
        return courseRepository.findByTeacherId(id);
    }

}
