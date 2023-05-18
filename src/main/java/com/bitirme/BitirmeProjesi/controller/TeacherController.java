package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.service.StudentService;
import com.bitirme.BitirmeProjesi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveTeacher")
    public ResponseEntity<Teacher> saveOgretmen(@RequestBody Teacher teacher) {
        Teacher teacher1 = teacherService.saveOgretmen(teacher);
        return new ResponseEntity<>(teacher1, HttpStatus.CREATED);
    }

    @GetMapping("/ogretmenListeleById/{id}")
    public ResponseEntity<Teacher> getOgretmenById(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getOgretmenById(id);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/getTeachers")
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teacherList = teacherService.getTeachers();
        return ResponseEntity.ok(teacherList);
    }

    @PutMapping("/updateTeacher")
    public ResponseEntity updateTeacher(@RequestBody TeacherDto dto){
        return teacherService.updateTeacherFromDto(dto);
    }
    @PostMapping("/deleteTeacher")
    public boolean deleteStudent(@RequestBody TeacherDto dto) {
        return teacherService.deleteTeacher(dto);
    }

    @GetMapping("/{teacherId}/courses/{courseCode}/students")
    public List<Student> getStudentsByTeacherAndCourse(@PathVariable Long teacherId, @PathVariable Long courseCode) {
        return teacherService.getStudentsByTeacherAndCourse(teacherId, courseCode);
    }

}
