package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.Teacher;
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
    public ResponseEntity<List<Teacher>> getOgretmenler() {
        List<Teacher> teacherList = teacherService.getOgretmenler();
        return ResponseEntity.ok(teacherList);
    }

    @PutMapping("/updateTeacher")
    public ResponseEntity updateTeacher(@RequestBody TeacherDto dto){
        return teacherService.updateTeacherFromDto(dto);
    }
}
