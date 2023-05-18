package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {
    void deleteByStudent(Student student);
    List<StudentCourse> findByCourse(Course course);
    void deleteByCourse(Course course);
    List<StudentCourse> findByStudent(Student student);
}
