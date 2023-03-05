package com.bitirme.BitirmeProjesi.repository;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import com.bitirme.BitirmeProjesi.entity.Ogrenci;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {
}
