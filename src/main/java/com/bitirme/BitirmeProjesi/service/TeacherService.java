package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.dtomapper.TeacherDtoMapper;
import com.bitirme.BitirmeProjesi.entity.*;
import com.bitirme.BitirmeProjesi.repo.CourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.TeacherRepository;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public TeacherService(TeacherRepository teacherRepository) {

    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher saveOgretmen(Teacher teacher) {
        try {
            String plainPassword = teacher.getUser().getPassword();
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
            teacher.getUser().setPassword(hashedPassword);
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            throw e;
        }
    }

    public Teacher getOgretmenById(Long id) {
        return teacherRepository.getOne(id);
    }

    public ResponseEntity updateTeacherFromDto(TeacherDto dto) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Teacher> oTeacher = teacherRepository.findById(dto.getId());
        if (oTeacher.isPresent()) {
            Teacher teacher = oTeacher.get();
            teacher.getUser().setUsername(dto.getOgretmen_no());
            hm.put("status", true);
            hm.put("result", oTeacher);
            TeacherDtoMapper teacherDtoMapper = Mappers.getMapper(TeacherDtoMapper.class);
            teacherDtoMapper.updateTeacherFromDto(dto, teacher);
            teacherRepository.save(teacher);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put("status", false);
        hm.put("message", "Update From Dto Failed");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public boolean deleteTeacher(TeacherDto dto) {
        Optional<Teacher> oTeacher = teacherRepository.findById(dto.getId());
        if (!oTeacher.isPresent()) {
            return false;
        }
        List<Course> courseList = courseRepository.findByTeacher(oTeacher.get());
        for (Course course : courseList) {
            List<StudentCourse> studentCourseList = studentCourseRepository.findByCourse(course);
            studentCourseRepository.deleteAll(studentCourseList);
        }
        userRepository.deleteById(dto.getUserid());
        teacherRepository.deleteById(dto.getId());
        return true;
    }

    public List<Student> getStudentsByTeacherAndCourse(Long teacherId, Long courseCode) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found with id: " + teacherId);
        }

        Course course = teacher.getCourses().stream()
                .filter(c -> c.getDersKodu().equals(courseCode))
                .findFirst()
                .orElse(null);

        if (course == null) {
            throw new IllegalArgumentException("Course not found with code: " + courseCode);
        }

        List<StudentCourse> studentCourses = studentCourseRepository.findByCourse(course);
        return studentCourses.stream()
                .map(StudentCourse::getStudent)
                .collect(Collectors.toList());
    }

}
