package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.dtomapper.TeacherDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.repo.TeacherRepository;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Teacher> getOgretmenler() {
        return teacherRepository.findAll();
    }

    public Teacher saveOgretmen(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getOgretmenById(Long id) {
        return teacherRepository.getOne(id);
    }

    public ResponseEntity updateTeacherFromDto(TeacherDto dto){
        Map<String,Object> hm = new LinkedHashMap<>();
        Optional<Teacher> oTeacher = teacherRepository.findById(dto.getOgretmen_Id());
        if (oTeacher.isPresent()){
            Teacher teacher = oTeacher.get();
            teacher.getUser().setUsername(dto.getOgretmen_no());
            hm.put("status", true);
            hm.put("result", oTeacher);
            TeacherDtoMapper teacherDtoMapper = Mappers.getMapper(TeacherDtoMapper.class);
            teacherDtoMapper.updateTeacherFromDto(dto,teacher);
            teacherRepository.save(teacher);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put("status",false);
        hm.put("message", "Update From Dto Failed");
        return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
    }
}
