package com.bitirme.BitirmeProjesi.service.impl;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import com.bitirme.BitirmeProjesi.entity.Ogrenci;
import com.bitirme.BitirmeProjesi.entity.Ogretmen;
import com.bitirme.BitirmeProjesi.repository.CourseRepository;
import com.bitirme.BitirmeProjesi.repository.StudentRepository;
import com.bitirme.BitirmeProjesi.repository.TeacherRepository;
import com.bitirme.BitirmeProjesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    @Override
    public Ogrenci saveOgrenci(Ogrenci ogrenci) {
        return studentRepository.save(ogrenci);
    }

    @Override
    public Ogretmen saveOgretmen(Ogretmen ogretmen) {
        return teacherRepository.save(ogretmen);
    }

    @Override
    public Dersler saveDers(Dersler dersler) {
        return courseRepository.save(dersler);
    }
}
