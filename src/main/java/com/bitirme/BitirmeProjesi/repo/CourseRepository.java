package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByDersKodu(Course ders_kodu);
}
